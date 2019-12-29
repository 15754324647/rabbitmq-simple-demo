package com.rabbitmq.provider.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息确认回调配置
 *
 * @author Graciano
 * @date 2019/12/29 23:01
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        //设置开启mandatory，才能触发回调函数，无论消息推送结果怎么样都强制用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean situation, String cause) {
                System.out.println("confirmCallBack:    " + "相关数据：" + correlationData);
                System.out.println("confirmCallBack:    " + "确认情况：" + situation);
                System.out.println("confirmCallBack:    " + "原因：" + cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int responseCode, String responseMessage, String exchange, String routingKey) {
                System.out.println("returnCallBack:     " + "消息：" + message);
                System.out.println("returnCallBack:     " + "回应码：" + responseCode);
                System.out.println("returnCallBack:     " + "回应信息：" + responseMessage);
                System.out.println("returnCallBack:     " + "交换机：" + exchange);
                System.out.println("returnCallBack:     " + "路由键：" + routingKey);

            }

        });


        return rabbitTemplate;
    }

}
