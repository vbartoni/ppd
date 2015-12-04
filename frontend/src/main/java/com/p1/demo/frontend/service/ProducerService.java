package com.p1.demo.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.demo.backend.domain.Customer;
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

    @Autowired
    private ObjectMapper mapper;

    public void send(final Customer customer) {
        System.out.println("send() enter");
        try {
            final String jsonMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);
            MessageCreator messageCreator = new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(jsonMessage);
                }
            };
            System.out.println("Sending new message.");
            jmsTemplate.send("pptQueue", messageCreator);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
