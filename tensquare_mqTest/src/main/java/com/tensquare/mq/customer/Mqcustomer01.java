package com.tensquare.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "testQ01")
public class Mqcustomer01 {

    @RabbitHandler
    public void customer01(String msg){
        System.out.println("2222收到消息:"+msg);
    }


}
