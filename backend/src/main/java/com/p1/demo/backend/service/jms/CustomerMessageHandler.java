package com.p1.demo.backend.service.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.demo.backend.domain.jpa.Customer;
import com.p1.demo.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * Created by bartpved on 2015-12-06.
 */
@Component
public class CustomerMessageHandler implements MessageHandler {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    CustomerRepository repository;

    @Override
    public void handle(Message msg) {
        TextMessage textMessage = (TextMessage) msg;
        Customer customer = null;
        try {
            customer = mapper.readValue(((TextMessage) msg).getText(), Customer.class);
        } catch (IOException | JMSException e) {
            e.printStackTrace();
        }
        repository.save(customer);
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer c : repository.findAll()) {
            System.out.println(c);
        }
    }
}
