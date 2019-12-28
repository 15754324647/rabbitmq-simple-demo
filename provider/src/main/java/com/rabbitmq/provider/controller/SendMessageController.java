package com.rabbitmq.provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author limeiqi
 * @date 2019/12/27
 **/

@RestController
@RequestMapping("/message")
public class SendMessageController {

    private static String createTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static String messageIdNow = String.valueOf(UUID.randomUUID());

    private Map<String, Object> map = new HashMap<>(3);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @ResponseBody
    @GetMapping(value = "/sendDirectMessage")
    public String sendDirectMessage() {
        String messageData = "testMessageData:love wu li wang";
        map = new HashMap<>(3);
        map.put("messageId", messageIdNow);
        map.put("messageData", messageData);
        map.put("createTime", createTimeNow);
        //将消息携带绑定键值 发送到交换机
        rabbitTemplate.convertAndSend("testDirectExchange", "testDirectRouting", map);
        return "OK";
    }

    @GetMapping("/sendTopicMessageMan")
    public String sendTopicMessageMan() {
        String messageData = "topic man message 我只绑定了这一个路由键 在topicExchange交换机上，只有消息携带的时topic.man的才会分发到该队列";
        map = new HashMap<>(3);
        map.put("messageId", messageIdNow);
        map.put("messageData", messageData);
        map.put("createTime", createTimeNow);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", map);
        return "done man";
    }


    @GetMapping("/sendTopicMessageTotal")
    public String sendTopicMessageTotal() {
        String messageData = "topic total message both 绑定 在topicExchange交换机上,绑定的键值为用上通配路由键规则topic.# ,这样只要是消息携带的路由键以topic.开头,都会被分发到该队列";
        map = new HashMap<>(3);
        map.put("messageId", messageIdNow);
        map.put("messageData", messageData);
        map.put("createTime", createTimeNow);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", map);
        return "done total";
    }
}
