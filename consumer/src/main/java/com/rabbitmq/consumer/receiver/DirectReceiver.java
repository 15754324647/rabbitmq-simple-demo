package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听的队列名称
 *
 * @author limeiqi
 * @date 2019/12/27
 **/
@Component
@RabbitListener(queues = "testDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(Map<String, Object> message) {
        System.out.println("DirectReceiver收到消费的消息：" + message.toString());
    }
}
