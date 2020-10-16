package com.example.snoopy.alarm.impl;

import com.example.snoopy.alarm.RpcAlarm;
import com.example.snoopy.config.SnoopyConfig;
import com.example.snoopy.model.RequestLog;
import j2html.tags.ContainerTag;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

import static j2html.TagCreator.*;

/**
 * @author maike
 * @date 2020/10/15
 */
@Component
public class EmailMsgAlarm implements RpcAlarm {

    @Override
    public boolean doAlarm(RequestLog log) {

        try {
            MimeMessage mimeMessage = SnoopyConfig.getConfig().mailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(SnoopyConfig.getConfig().getEmailFrom(), "SNOOPY");
            helper.setTo("maike@dian.so");
            helper.setSubject("Rpc Alarm");
            helper.setText(htmlText(log), true);
            SnoopyConfig.getConfig().mailSender().send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String htmlText(RequestLog log) {
        ContainerTag rpc_alarm = html(
                head(title("RPC Alarm")),
                body(h2(log.getAppName()),
                        li(log.getClassName() + log.getMethodName()),
                        li(log.getHttpMethod() + log.getHttpUrl()),
                        li(log.getException()),
                        li(log.getHttpResult()),
                        li(log.getOwner())));
        return rpc_alarm.renderFormatted();
    }
}
