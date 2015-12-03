package com.p1.demo.backend;

import com.p1.demo.backend.domain.Customer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;

@SpringBootApplication
@EnableJms
public class Application{

    @Autowired
    CustomerRepository repository;

    @Bean
    public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
        ActiveMQConnectionFactory amqcf = new ActiveMQConnectionFactory();
        amqcf.setBrokerURL("tcp://localhost:61616");
        return amqcf;
    }

    @Bean(name="myJmsContainerFactory")
    public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMQConnectionFactory());
        return factory;
    }

    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        System.out.println("Starting backend application");
        SpringApplication.run(Application.class);
        System.out.println("Backend application started");
    }

    public void runDatabase(String... strings) throws Exception {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : repository.findByLastName("Bauer")) {
            System.out.println(bauer);
        }
    }

}
