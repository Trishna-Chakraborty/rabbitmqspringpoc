package com.example.rabbitmqspringpoc;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;

import java.io.IOException;

public class Server {


    //https://www.javacodegeeks.com/2018/02/springboot-messaging-rabbitmq.html
    //sent message as json
   // @RabbitListener(queues = "customer")
    //@SendTo("reply")
    public int fibonacci(int n, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println(" [x] Received request for " + n);
        if(n>=10){channel.basicAck(tag,false); return 0;}
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        channel.basicAck(tag,false);
        return result;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

    @RabbitListener(queues = "dead_queue")
    @SendTo("reply_queue")
    public int deadfibonacci(int n , Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println(" [x] Received request for dead " + n);
        if(n>=10){channel.basicAck(tag,false); return 0;}
        int result = fib(n);

        System.out.println(" [.] Returned dead " + result);
        channel.basicAck(tag,false);
        return result;
    }



}
