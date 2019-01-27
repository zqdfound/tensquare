package com.tensquare.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RabbitListener(queues = "sms")
public class Customer02 {

    @RabbitHandler
    public void customer(HashMap<String,Object> map){
        System.out.println("电话："+map.get("mobile"));
        System.out.println("验证码："+map.get("checkcode"));
    }
}
