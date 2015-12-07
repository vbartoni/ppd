package com.p1.demo.backend.service.jms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.demo.backend.domain.jms.CollectdData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.List;

/**
 * Created by bartpved on 2015-12-06.
 */

@Component
public class CollectdMessageHandler implements MessageHandler{

    @Autowired
    ObjectMapper mapper;

    @Override
    public void handle(Message message){
        try {
            String msg = ((TextMessage) message).getText();
            List<CollectdData> collectdDataList;
            System.out.println(msg);
            collectdDataList = mapper.readValue(msg, new TypeReference<List<CollectdData>>(){});
            for (CollectdData data:collectdDataList){
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
        } catch (JMSException |IOException e) {
            e.printStackTrace();
        }
    }
}
