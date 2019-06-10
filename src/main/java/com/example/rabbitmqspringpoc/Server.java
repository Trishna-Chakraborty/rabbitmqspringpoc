package com.example.rabbitmqspringpoc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Server {

    @RabbitListener(queues = "spring-boot")
 /*   public void receive(String msg){
        System.out.println("Received message "+ msg);
    }*/
    public int fibonacci(int n) {
        System.out.println(" [x] Received request for " + n);
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

}
