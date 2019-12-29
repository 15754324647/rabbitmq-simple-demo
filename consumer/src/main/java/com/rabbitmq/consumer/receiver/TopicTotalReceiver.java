package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 主题交换机-监听的队列名称topic.woman
 *
 * @author graciano
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("topic total 监听到消息：" + map.toString());
    }


}
