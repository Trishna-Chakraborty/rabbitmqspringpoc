package com.example.rabbitmqspringpoc;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        MessageProperties props = new MessageProperties();
        props.setReplyTo("reply-queue");

        Message toSend = new Message(no.toString().getBytes(), props);
        Integer response= (Integer)rabbitTemplate.convertSendAndReceive("customer_exchange", "",toSend);
        System.out.println(" [.] Got '" + response + "'");
    }


}
