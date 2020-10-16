package com.example.snoopy;

import com.example.snoopy.core.LogResolverThreadHelper;
import com.example.snoopy.dao.RequestLogDAO;
import com.netflix.discovery.EurekaClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component
public class LogConsumer {

    @Resource
    private RequestLogDAO logDAO;

    @Resource
    private EurekaClient eurekaClient;

    @KafkaListener(topics = "demo_kafka_logs", groupId = "demo", containerFactory = "batchFactory")
    public void consumer(List<String> records, Acknowledgment ack) throws SQLException {
        if (!CollectionUtils.isEmpty(records)) {
            // 线程池处理
            for (String record : records) {
                // todo 异常处理
                LogResolverThreadHelper.resolver(record);
            }
            ack.acknowledge();
        }
    }
}
