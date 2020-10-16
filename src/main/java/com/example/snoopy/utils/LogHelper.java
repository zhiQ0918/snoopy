package com.example.snoopy.utils;

import com.alibaba.fastjson.JSON;
import com.example.snoopy.LogConsumer;
import com.example.snoopy.annotation.FeignClient;
import com.example.snoopy.model.Body;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 日志格式定义
 *
 * @author maike
 * @date 2020/10/14
 */
@Slf4j(topic = "life_log")
public class LogHelper {

    private String feignClazz;
    private String feignMethod;
    private String httpUrl;
    private String httpMethod;
    private String param;
    private int retryCount;
    private int alarmType;

    private static LogConsumer consumer;

    public LogHelper(String feignClazz, String feignMethod, String httpUrl, String httpMethod, String param, int retryCount, int alarmType) {
        this.feignClazz = feignClazz;
        this.feignMethod = feignMethod;
        this.httpUrl = httpUrl;
        this.httpMethod = httpMethod;
        this.param = param;
        this.retryCount = retryCount;
        this.alarmType = alarmType;
    }

    /**
     * todo 对feignClass和feignMethod的反射调用可以使用缓存
     * @param body
     * @param e
     */
    public static void log(Body body, Throwable e) {
        String targetAppName = null;
        String[] url = null;
        String httpMethod = null;
        if (body.getFeignClazz() != null) {
            Annotation feignAnnotation = body.getFeignClazz().getAnnotation(FeignClient.class);
            if (feignAnnotation instanceof FeignClient) {
                targetAppName = ((FeignClient) feignAnnotation).name();
            }
            Method[] declaredMethods = body.getFeignClazz().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                System.out.println(declaredMethod.getName());
            }
            Method remoteMethod = null;
            try {
                remoteMethod = body.getFeignClazz().getDeclaredMethod(body.getFeignMethod(),String.class);
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            Annotation[] methodAnnotations = remoteMethod.getAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {

                if (methodAnnotation instanceof GetMapping) {
                    url = ((GetMapping) methodAnnotation).value();
                    httpMethod = RequestMethod.GET.toString().toLowerCase();
                } else if (methodAnnotation instanceof PostMapping) {
                    url = ((PostMapping) methodAnnotation).value();
                    httpMethod = RequestMethod.POST.toString().toLowerCase();
                } else if (methodAnnotation instanceof RequestMapping) {
                    url = ((RequestMapping) methodAnnotation).value();
                    httpMethod = ((RequestMapping) methodAnnotation).method()[0].toString().toLowerCase();
                }
            }
        }
        body.setHttpUrl(url);
        body.setHttpMethod(httpMethod);
        body.setFeignTargetAppName(targetAppName);
        System.out.println(JSON.toJSONString(body));
        log.info(JSON.toJSONString(body),e);
    }

    public static Body.BodyBuilder getBuilder() {
        return Body.builder();
    }
}
