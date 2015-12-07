package com.p1.demo.frontend.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.demo.backend.domain.jms.MessageType;
import com.p1.demo.backend.domain.jpa.Customer;
import com.p1.demo.frontend.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    ProducerService ds;

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping("/greeting")
    public Customer greeting(@RequestParam(value="firstName", defaultValue="Vedran") String firstName,
                             @RequestParam(value="lastName", defaultValue="Bartonicek") String lastName) {

        Customer customer = new Customer(firstName, lastName);
        try {
            final String jsonMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);
            ds.send(jsonMessage, MessageType.CUSTOMER);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
