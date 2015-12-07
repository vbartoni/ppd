package com.p1.demo.backend.service.jms;

import com.p1.demo.backend.domain.jms.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by vbartoni on 8/16/15.
 */
@Component
public class MessageReceiever {

    @Autowired
    CustomerMessageHandler customerHandler;

    @Autowired
    CollectdMessageHandler metricHandler;

    @JmsListener(destination = "pptQueue", containerFactory = "defaultJmsListenerContainerFactory")
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("Received jms message");
                int msgType = message.getIntProperty("MSG_TYPE");
                switch (msgType) {
                    case MessageType.CUSTOMER:
                        customerHandler.handle(message);
                        break;
                    case MessageType.COLLECTD_DATA:
                        metricHandler.handle(message);
                        break;
                    default:
                        System.out.println("Unknown message type");
                }
            }catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            throw new IllegalArgumentException("MessageType must be of type TextMessage");
        }
    }
}
