package com.example.snoopy.config;

import com.example.snoopy.alarm.RpcAlarmExecutor;
import com.example.snoopy.core.LogResolverThreadHelper;
import com.example.snoopy.core.RetryThreadHelper;
import com.example.snoopy.dao.RequestLogDAO;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class SnoopyConfig implements InitializingBean, DisposableBean {

    private static SnoopyConfig config = null;
    public static SnoopyConfig getConfig(){
        return config;
    }

    @Override
    public void destroy() throws Exception {
        RetryThreadHelper.getInstance().toStop();
        LogResolverThreadHelper.toStop();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        config = this;
//        RetryThreadHelper.getInstance().start();
//        LogResolverThreadHelper.toStart();
    }

    @Resource
    private RequestLogDAO logDAO;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RpcAlarmExecutor alarmExecutor;

    @Resource
    private EurekaClient eurekaClient;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public RequestLogDAO getLogDAO() {
        return logDAO;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public JavaMailSender mailSender() {
        return mailSender;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public RpcAlarmExecutor getAlarmExecutor() {
        return alarmExecutor;
    }

    public EurekaClient getEurekaClient() {
        return eurekaClient;
    }
}
