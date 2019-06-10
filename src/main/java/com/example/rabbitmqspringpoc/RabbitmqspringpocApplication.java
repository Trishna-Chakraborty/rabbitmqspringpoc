package com.example.rabbitmqspringpoc;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class RabbitmqspringpocApplication {



    @Bean
    Queue replyQueue() {
        return new Queue("reply_queue", false);
    }

    @Bean
    Queue deadQueue() {
        return new Queue("dead_queue", false);
    }

    @Bean
    DirectExchange deadExchange() {
        return new DirectExchange("dead_exchange");
    }

    @Bean
    Binding deadBinding(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("");
    }


    @Bean
    Queue customerQueue() {

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", "dead_exchange");
        args.put("x-message-ttl", 60000);
        return new Queue("customer", false, false, false, args);
    }

    @Bean
    DirectExchange customerExchange() {
        return new DirectExchange("customer_exchange");
    }

    @Bean
    Binding customerBinding(Queue customerQueue, DirectExchange customerExchange) {
        return BindingBuilder.bind(customerQueue).to(customerExchange).with("");
    }

 /*   @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }*/


   @Bean
   Server server(){
       return  new Server();
   }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqspringpocApplication.class, args);
    }

}
