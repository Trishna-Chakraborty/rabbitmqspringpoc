package com.example.rabbitmqspringpoc;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sender {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    int no=1;


    @GetMapping("/send")
    public void send(){
        no++;
        //System.out.println("Sending message...");
        //rabbitTemplate.convertAndSend(RabbitmqspringpocApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        System.out.println(" [x] Requesting fib(" + no + ")");
        Integer response= (Integer)rabbitTemplate.convertSendAndReceive(RabbitmqspringpocApplication.topicExchangeName, "foo.bar.baz", no);
        System.out.println(" [.] Got '" + response + "'");
    }


}
