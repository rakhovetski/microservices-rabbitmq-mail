package ru.stashevskaya.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding queueBinding() {
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }
}
