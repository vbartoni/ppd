package com.p1.demo.frontend;

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



//    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
//    <property name="connectionFactory" ref="jmsCFP" />
//    <property name="receiveTimeout" value="100" />
//    <!-- This is important... -->
//    <property name="sessionTransacted" value="true" />
//    </bean>

//    <bean id="jmsCFP" class="org.apache.activemq.pool.PooledConnectionFactory" destroy-method="stop">
//    <property name="connectionFactory">
//    <bean class="org.apache.activemq.ActiveMQConnectionFactory">
//    <property name="brokerURL">
//    <value>vm://localhost</value>
//    </property>
//    </bean>
//    </property>
//    </bean>

    @Bean(destroyMethod = "stop")
    public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
        ActiveMQConnectionFactory amqcf = new ActiveMQConnectionFactory();
        amqcf.setBrokerURL("tcp://localhost:61616");
        return amqcf;
    }

//    @Bean(destroyMethod = "stop")
//    public PooledConnectionFactory getPooledConnectionFactory(){
//        PooledConnectionFactory pcf = new PooledConnectionFactory();
//        pcf.setConnectionFactory(getActiveMQConnectionFactory());
//        return pcf;
//    }

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
