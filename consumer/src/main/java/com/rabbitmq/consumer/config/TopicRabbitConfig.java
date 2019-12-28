package com.rabbitmq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机
 *
 * @author graciano
 */
@Configuration
public class TopicRabbitConfig {

    private final static String TOPIC_MAN = "topic.man";
    private final static String TOPIC_WOMAN = "topic.woman";

    @Bean
    public Queue topicManQueue() {
        return new Queue(TOPIC_MAN);
    }

    @Bean
    public Queue topicWomanQueue() {
        return new Queue(TOPIC_WOMAN);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingMan() {
        return BindingBuilder.bind(topicManQueue()).to(topicExchange()).with(TOPIC_MAN);
    }

    @Bean
    Binding bindingTotal() {
        return BindingBuilder.bind(topicWomanQueue()).to(topicExchange()).with("topic.#");
    }
}
