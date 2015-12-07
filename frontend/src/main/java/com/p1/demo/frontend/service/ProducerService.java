package com.p1.demo.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by vbartoni on 8/16/15.
 */
@Service
public class ProducerService {
    @Autowired
    JmsTemplate jmsTemplate;

    public void send(final String msg, final int msgType) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(msg);
                message.setIntProperty("MSG_TYPE", msgType);
                return message;
            }
        };
        System.out.println("Sending new message.");
        jmsTemplate.send("pptQueue", messageCreator);
    }
}
