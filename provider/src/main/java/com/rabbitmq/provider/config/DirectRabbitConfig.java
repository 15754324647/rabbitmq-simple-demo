package com.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct exchange 直连交换机
 *
 * @author limeiqi
 * @date 2019/12/27
 **/
@Configuration
public class DirectRabbitConfig {

    /**
     * 队列
     *
     * @return 持久队列
     */
    @Bean
    public Queue testDirectQueue() {
        return new Queue("testDirectQueue", true);
    }

    /**
     * 交换机
     *
     * @return 交换机
     */
    @Bean
    DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }

    /**
     * 队列交换机绑定
     *
     * @return 设置匹配的路由键
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }

}
