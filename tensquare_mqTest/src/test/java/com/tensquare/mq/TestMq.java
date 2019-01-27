package com.tensquare.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApplication.class)
public class TestMq {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void queuesProducter(){
        rabbitTemplate.convertAndSend("testQ01","队列测试消息");
    }
}
