package com.example.snoopy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Body {

    private String feignTargetAppName;
    private Class feignClazz;
    private String feignMethod;
    private String[] httpUrl;
    private String httpMethod;
    private Class[] paramTypes;
    private int retryCount;
    private int alarmType;
    private String exceptionMsg;

    public Body(Class feignClazz, String feignMethod, String[] httpUrl, String httpMethod, Class[] paramTypes, int retryCount, int alarmType,String exceptionMsg) {
        this.feignClazz = feignClazz;
        this.feignMethod = feignMethod;
        this.httpUrl = httpUrl;
        this.httpMethod = httpMethod;
        this.paramTypes = paramTypes;
        this.retryCount = retryCount;
        this.alarmType = alarmType;
        this.exceptionMsg = exceptionMsg;
    }

    public static Body.BodyBuilder builder(){
        return new Body.BodyBuilder();
    }

    public static class BodyBuilder {
        private Class feignClazz;
        private String feignMethod;
        private String[] httpUrl;
        private String httpMethod;
        private Class[] paramTypes;
        private int retryCount;
        private int alarmType;
        private String exceptionMsg;

        public Body build(){
            return new Body(this.feignClazz,feignMethod,httpUrl,httpMethod,paramTypes,retryCount,alarmType,exceptionMsg);
        }

        public BodyBuilder feignClazz(Class feignClazz){
            this.feignClazz = feignClazz;
            return this;
        }

        public BodyBuilder feignMethod(String feignMethod){
            this.feignMethod = feignMethod;
            return this;
        }

        public BodyBuilder httpUrl(String[] httpUrl){
            this.httpUrl = httpUrl;
            return this;
        }

        public BodyBuilder httpMethod(String httpMethod){
            this.httpMethod = httpMethod;
            return this;
        }

        public BodyBuilder paramTypes(Class[] paramTypes){
            this.paramTypes = paramTypes;
            return this;
        }

        public BodyBuilder retryCount(int retryCount){
            this.retryCount = retryCount;
            return this;
        }

        public BodyBuilder alarmType(int alarmType){
            this.alarmType = alarmType;
            return this;
        }

        public BodyBuilder exceptionMsg(String exceptionMsg){
            this.exceptionMsg = exceptionMsg;
            return this;
        }
    }
}
