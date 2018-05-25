package com.aaxis.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        log.info("Msg Received: <" + email + ">");
    }
}
