package com.p1.demo.frontend.mvc;

import com.p1.demo.backend.domain.jms.MessageType;
import com.p1.demo.backend.domain.jpa.Customer;
import com.p1.demo.frontend.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    //public Customer postData(@RequestBody List<CollectdData> collectdData) {
    public Customer postData(@RequestBody String collectdData) {

        /*
        for (CollectdData data:collectdData){
            System.out.println("--------------------------------------");
            System.out.println("values:" + data.getValues());
            System.out.println("host:" + data.getHost());
            System.out.println("interval:" + data.getInterval());
            System.out.println("plugin :" + data.getPlugin());
            System.out.println("plugin instance:" + data.getPlugin_instance());
            System.out.println("time:" + data.getTime());
            System.out.println("type:" + data.getType());
            System.out.println("type instance:" + data.getType_instance());
            System.out.println("dsnames:" + data.getDsnames());
            System.out.println("dstypes:" + data.getDstypes());
        }
        */

        System.out.println("received collectd data:");
        System.out.println(collectdData);
        producer.send(collectdData, MessageType.COLLECTD_DATA);
        return null;
    }

}
