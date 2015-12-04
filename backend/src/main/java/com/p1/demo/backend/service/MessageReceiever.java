package com.p1.demo.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.demo.backend.repository.CustomerRepository;
import com.p1.demo.backend.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * Created by vbartoni on 8/16/15.
 */
@Component
public class MessageReceiever {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    CustomerRepository repository;

    @JmsListener(destination = "pptQueue", containerFactory = "defaultJmsListenerContainerFactory")
    public void onMessage(Message message) {
        if (message instanceof TextMessage) try {
           String msg = ((TextMessage) message).getText();
           System.out.println("Received " + msg);

           Customer customer = mapper.readValue(msg, Customer.class);
           repository.save(customer);
           System.out.println("Customers found with findAll():");
           System.out.println("-------------------------------");
           for (Customer c : repository.findAll()) {
               System.out.println(c);
           }
        }catch (JMSException | IOException ex) {
           throw new RuntimeException(ex);
        }else{
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
