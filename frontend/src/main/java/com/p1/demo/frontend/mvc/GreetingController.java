package com.p1.demo.frontend.mvc;

import java.util.concurrent.atomic.AtomicLong;

import com.p1.demo.frontend.domain.Greeting;
import com.p1.demo.frontend.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    ProducerService ds;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        ds.send();
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
