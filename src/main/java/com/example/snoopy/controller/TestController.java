package com.example.snoopy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.snoopy.BizResult;
import com.example.snoopy.config.SnoopyConfig;
import com.example.snoopy.dao.RequestLogDAO;
import com.example.snoopy.model.RequestLog;
import com.example.snoopy.utils.LogHelper;
import com.example.snoopy.RemoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
@RestController
public class TestController {

    @Resource
    private RequestLogDAO logDAO;

    @GetMapping(value = "queryLog")
    private List<RequestLog> queryLogs() {
        ResponseEntity<String> responseEntity = SnoopyConfig.getConfig().getRestTemplate().getForEntity("http://10.108.175.215:8080/preload", String.class);
        return logDAO.query();
    }

    @GetMapping(value = "/shop/list")
    private BizResult<JSONObject> test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount",20);
        jsonObject.put("pageNo",1);

        List<JSONObject> objectList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            JSONObject object = new JSONObject();
            object.fluentPut("shopId", i)
                    .fluentPut("shopName", "门店名称" + i)
                    .fluentPut("callno", "callno" + 1)
                    .fluentPut("status", i % 4)
                    .fluentPut("createTime", LocalDateTime.now().toString());
            objectList.add(object);
        }
        jsonObject.put("data",objectList);
        Throwable throwable = null;
        LogHelper.log(LogHelper.getBuilder().feignClazz(RemoteService.class).feignMethod("feignMethod").paramTypes(new Class[]{String.class}).build(), throwable);
        return BizResult.create(jsonObject);
    }




}
