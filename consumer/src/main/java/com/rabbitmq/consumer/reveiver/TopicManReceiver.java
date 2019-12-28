package com.rabbitmq.consumer.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 主题交换机-监听的队列topic.man
 *
 * @author graciano
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("topic.man 监听到消息：" + map.toString());
    }


}
