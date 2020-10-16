package com.example.snoopy.core;

import com.alibaba.fastjson.JSON;
import com.example.snoopy.DefaultSettings;
import com.example.snoopy.config.SnoopyConfig;
import com.example.snoopy.model.Body;
import com.example.snoopy.model.RequestLog;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 提供一个线程池用于解析kafka消息并负责存储
 *
 * @author maike
 * @date 2020/10/14
 */
@Slf4j
public class LogResolverThreadHelper {

    /**
     * todo 需要可配置
     */
    private ThreadPoolExecutor logResolverThreadPool = null;

    public void start() {
        logResolverThreadPool = new ThreadPoolExecutor(
                10,
                200,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                r -> new Thread(r, "RequestLog, logResolverThreadPool-" + r.hashCode()));
    }

    public void stop() {
        logResolverThreadPool.shutdownNow();
    }

    public void addResolver(String record) {
        // todo 放入一个容器中,可以先行判断是否已经在执行中了
        logResolverThreadPool.execute(() -> {
            String[] items = record.split("#");
            String createTime = items[0].trim();
            String level = items[1].trim();
            String body = items[2].trim();
            String exceptionMsg = null;
            Body bodyContent = null;
            if (body.endsWith("}")) {
                bodyContent = JSON.parseObject(body, Body.class);
            } else {
                String[] bodyItems = body.split("}");
                bodyContent = JSON.parseObject(bodyItems[0] + "}", Body.class);
                exceptionMsg = bodyItems[1];
                bodyContent.setExceptionMsg(exceptionMsg);
            }

            RequestLog log = new RequestLog();

            // todo
            log.setAppName("hulk");
            log.setClassName("");
            log.setMethodName("");
            log.setDesc("desc");
            log.setException(exceptionMsg);
            log.setHttpUrl(JSON.toJSONString(bodyContent.getHttpUrl()));
            log.setHttpMethod(bodyContent.getHttpMethod());
            // todo
            log.setHttpParam("");
            log.setAlarmType(DefaultSettings.DEFAULT_ALARM_TYPE);
            log.setAlarmStatus(DefaultSettings.DEFAULT_ALARM_STATUS);
            log.setStatus(DefaultSettings.DEFAULT_STATUS);
            log.setCreateTime(LocalDateTime.now());
            // todo
            log.setOwner("");
            log.setRetryCount(DefaultSettings.DEFAULT_RETRY_COUNT);
            log.setUpdateTime(LocalDateTime.now());
            SnoopyConfig.getConfig().getLogDAO().insert(log);
        });
    }

    private static LogResolverThreadHelper helper = new LogResolverThreadHelper();

    public static void toStart() {
        helper.start();
    }

    public static void toStop() {
        helper.stop();
    }

    public static void resolver(String record) {
        helper.addResolver(record);
    }

}
