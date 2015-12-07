package com.p1.demo.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.FileSystemUtils;

import java.io.File;

@SpringBootApplication
public class Application {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }


    @Bean
    public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
        ActiveMQConnectionFactory amqcf = new ActiveMQConnectionFactory();
        amqcf.setBrokerURL("tcp://localhost:61616");
        return amqcf;
    }

    @Bean
    public JmsTemplate getJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(getActiveMQConnectionFactory());
        jmsTemplate.setReceiveTimeout(100);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);

        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    }
}
