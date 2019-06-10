package com.example.rabbitmqspringpoc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;

public class Server {


    //https://www.javacodegeeks.com/2018/02/springboot-messaging-rabbitmq.html
    //sent message as json
   /* @RabbitListener(queues = "customer")
    @SendTo("reply_queue")*/
    public int fibonacci(int n) {
        System.out.println(" [x] Received request for " + n);
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

    @RabbitListener(queues = "dead_queue")
    @SendTo("reply_queue")
    public int deadfibonacci(int n) {
        System.out.println(" [x] Received request for dead " + n);
        int result = fib(n);
        System.out.println(" [.] Returned dead " + result);
        return result;
    }



}
