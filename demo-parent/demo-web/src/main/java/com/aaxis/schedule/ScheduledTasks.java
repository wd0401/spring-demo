package com.aaxis.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    JmsTemplate jmsTemplate;


    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
            log.info("The time is now {}", dateFormat.format(new Date()));
            jmsTemplate.convertAndSend("mailbox", new Email("111@aa.com", "this is email body!"));
    }
}
