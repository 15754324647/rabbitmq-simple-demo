package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author limeiqi
 * @date 2019/12/29
 **/
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutBReceiver {


    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("扇形交换机绑定的队列：fanout.B---" + map.toString());
    }

}
