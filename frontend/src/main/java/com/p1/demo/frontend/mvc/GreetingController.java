package com.p1.demo.frontend.mvc;

import com.p1.demo.backend.domain.Customer;
import com.p1.demo.frontend.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    ProducerService ds;

    @RequestMapping("/greeting")
    public Customer greeting(@RequestParam(value="firstName", defaultValue="Vedran") String firstName,
                             @RequestParam(value="lastName", defaultValue="Bartonicek") String lastName) {
        Customer customer = new Customer(firstName, lastName);
        ds.send(customer);
        return customer;
    }
}
