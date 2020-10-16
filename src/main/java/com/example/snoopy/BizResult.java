package com.example.snoopy;

import lombok.Data;

/**
 *
 * @author maike
 * @date 2020/10/14
 */
@Data
public class BizResult<T> {

    private String message;
    private T result;
    private Integer status;
    private Long timestamp;

    public static <T> BizResult<T> create() {
        return new BizResult();
    }

    public static <T> BizResult<T> create(T data) {
        BizResult<T> bizResult = create();
        bizResult.setStatus(200);
        bizResult.setResult(data);
        bizResult.setTimestamp(System.currentTimeMillis());
        return bizResult;
    }

    public static <T> BizResult<T> create(T data, String code, String message) {
        BizResult<T> result = create();
        result.setStatus(200);
        result.setResult(data);
        result.setTimestamp(System.currentTimeMillis());
        result.setMessage(message);
        return result;
    }
}
