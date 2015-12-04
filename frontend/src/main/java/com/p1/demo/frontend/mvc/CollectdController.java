package com.p1.demo.frontend.mvc;

import com.p1.demo.backend.domain.Customer;
import com.p1.demo.frontend.domain.CollectdData;
import com.p1.demo.frontend.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bartpved on 2015-12-04.
 */
//@RequestMapping("/collectd")
@RestController
public class CollectdController {
    @Autowired
    ProducerService producer;

    @RequestMapping(value = "/ppd/collectd/data",
                    method = RequestMethod.POST,
                    headers = {"Content-type=application/json"})
    public Customer postData(@RequestBody List<CollectdData> collectdData) {
        System.out.println("Received data from collectd: " + collectdData.toString());

        /*for (){

        }*/

        CollectdData cd = collectdData.get(0);
        System.out.println("collectd data timestamp:" + cd.getTime());
        System.out.println("collectd data values:" + cd.getValues());
        System.out.println("collectd data " + cd);

        Customer customer = new Customer("data", "data");
        //producer.send(customer);
        return customer;
    }

}
