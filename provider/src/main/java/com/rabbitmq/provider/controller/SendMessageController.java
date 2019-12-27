package com.rabbitmq.provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author limeiqi
 * @date 2019/12/27
 **/

@RestController
@RequestMapping("/direct")
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "testMessageData:wu li xiao wang";
        String createTime = LocalDateTime.now().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值 发送到交换机
        rabbitTemplate.convertAndSend("testDirectExchange", "testDirectRouting", map);
        return "OK";
    }
}
