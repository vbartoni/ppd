package com.p1.demo.backend.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by vbartoni on 8/16/15.
 */
@Component
public class MessageReceiever {
    @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("Received " + ((TextMessage) message).getText());
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
/*
System.out.println("Received <" + message + ">");
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
*/