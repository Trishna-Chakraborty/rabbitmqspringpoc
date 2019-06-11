package com.example.rabbitmqspringpoc;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Sender {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    Integer no=1;


    @GetMapping("/send")
    public void send(){
        no++;
        //System.out.println("Sending message...");
        //rabbitTemplate.convertAndSend(RabbitmqspringpocApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        System.out.println(" [x] Requesting fib(" + no + ")");

    /*    MessageProperties props = new MessageProperties();
        props.setReplyTo("reply");*/



        //Message toSend = new Message(no.toString().getBytes(), props);
        rabbitTemplate.convertAndSend("customer_exchange", "",no);
        //System.out.println(" [.] Got '" + response + "'");
    }


    @RabbitListener(queues = "reply_queue")
    public void fibonacci(int n) throws IOException {
        System.out.println(" [.] Got '" +n+ " in reply");

    }


}
