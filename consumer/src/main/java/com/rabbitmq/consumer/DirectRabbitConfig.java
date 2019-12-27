package com.rabbitmq.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author limeiqi
 * @date 2019/12/27
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue testDirectQueue() {
        return new Queue("testDirectQueue", true);
    }


    @Bean
    DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }
}
