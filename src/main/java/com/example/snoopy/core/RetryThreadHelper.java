package com.example.snoopy.core;

import com.alibaba.fastjson.JSON;
import com.example.snoopy.config.SnoopyConfig;
import com.example.snoopy.enums.AlarmStatusEnum;
import com.example.snoopy.model.RequestLog;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 负责扫描符合重试规则的记录并进行重试
 *
 * @author maike
 * @date 2020/10/14
 */
public class RetryThreadHelper {

    private static Logger logger = LoggerFactory.getLogger("RetryThreadHelper");

    private static RetryThreadHelper instance = new RetryThreadHelper();

    public static RetryThreadHelper getInstance() {
        return instance;
    }

    private Thread retryThread;
    private volatile boolean toStop = false;

    public void start() {
        retryThread = new Thread(() -> {
            while (!toStop) {
                try {
                    List<RequestLog> requestLogs = SnoopyConfig.getConfig().getLogDAO().query();
                    for (int i = 0; i < requestLogs.size(); i++) {
                        try {
                            RequestLog log = requestLogs.get(i);
                            Application application = SnoopyConfig.getConfig().getEurekaClient().getApplication(log.getAppName());
                            if (application == null || CollectionUtils.isEmpty(application.getInstances())) {
                                logger.error("request appName resolver result application = {} , and instances is empty", JSON.toJSONString(application));
                                continue;
                            }
                            String ipAddr = application.getInstances().get(0).getInstanceId();
                            String url = "http://" + ipAddr.trim();
                            if (log.getHttpUrl().startsWith("/")) {
                                url += log.getHttpUrl();
                            } else {
                                url += "/" + log.getHttpUrl();
                            }
                            ResponseEntity<String> responseEntity = null;
                            if ("get".equalsIgnoreCase(log.getHttpMethod())) {
                                responseEntity = SnoopyConfig.getConfig().getRestTemplate().getForEntity(url, String.class, log.getHttpParam());
                            } else if ("post".equalsIgnoreCase(log.getHttpMethod())) {
                                responseEntity = SnoopyConfig.getConfig().getRestTemplate().postForEntity(url, log.getHttpParam(), String.class);
                            } else {
                                // todo 暂不支持
                            }
                            // todo 处理responseEntity为空的问题
                            System.out.println("================" + JSON.toJSONString(responseEntity));
                            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                                SnoopyConfig.getConfig().getLogDAO().updateResult(log.getId(), JSON.toJSONString(responseEntity));
                            }
                            log.setRetryCount(log.getRetryCount() - 1);
                            if (log.getRetryCount() == 0) {
                                boolean alarmResult = SnoopyConfig.getConfig().getAlarmExecutor().alarm(log);
                                SnoopyConfig.getConfig().getLogDAO().updateAlarmResult(log.getId(), alarmResult ? AlarmStatusEnum.success.getStatus() : AlarmStatusEnum.fail.getStatus());
                            }

                            SnoopyConfig.getConfig().getLogDAO().updateLog(log);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    if (!toStop) {
                        logger.error(">>>>>>>>>>> retry thread error:{}", e);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    if (!toStop) {
                        logger.error(">>>>>>>>>>> retry thread error:{}", e);
                    }

                }
            }
        });
        retryThread.setDaemon(true);
        retryThread.setName("retry-thread");
        retryThread.start();

    }

    public void toStop() {
        toStop = true;
        // interrupt and wait
        retryThread.interrupt();
        try {
            retryThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

