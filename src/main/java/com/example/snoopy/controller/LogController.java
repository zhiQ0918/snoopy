package com.example.snoopy.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.snoopy.BizResult;
import com.example.snoopy.RemoteService;
import com.example.snoopy.dao.RequestLogDAO;
import com.example.snoopy.enums.AlarmStatusEnum;
import com.example.snoopy.enums.AlarmTypeEnum;
import com.example.snoopy.enums.LogStatusEnum;
import com.example.snoopy.model.RequestLog;
import com.example.snoopy.utils.LogHelper;
import com.example.snoopy.vo.LogVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maike
 * @date 2020/10/15
 */
@RestController
public class LogController {

    @Resource
    private RequestLogDAO logDAO;

    @GetMapping("log/list")
    public BizResult<JSONObject> list() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", 20);
        jsonObject.put("pageNo", 1);

        List<RequestLog> query = logDAO.query();
        List<LogVO> logVOS = new ArrayList<>();
        for (RequestLog log : query) {
            LogVO vo = new LogVO();
            vo.setLogId(log.getId());
            vo.setAppName(log.getAppName());
            vo.setCreateTime(log.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            vo.setUpdateTime(log.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            vo.setDesc(log.getDesc());
            vo.setRetryCount(log.getRetryCount());
            vo.setOwner(log.getOwner());
            vo.setStatus(LogStatusEnum.getDesc(log.getStatus()));
            vo.setHttpInfo("url: " + log.getHttpUrl() + "\nmethod: " + log.getHttpMethod() + "\nparam: " + log.getHttpParam() + "\nresult: " + log.getHttpResult());
            vo.setAlarmInfo("alarmType: " + AlarmTypeEnum.getDesc(log.getAlarmType()) + "\nalarmStatus: " + AlarmStatusEnum.getDesc(log.getAlarmStatus()));
            vo.setExceptionInfo("className: " + log.getClassName() + "\nmethodName: " + log.getMethodName() + "\nexception: " + log.getException());
            logVOS.add(vo);
        }
        jsonObject.put("data", logVOS);
        return BizResult.create(jsonObject);
    }

}
