package ru.stashevskaya.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.util.GsonFactory;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationProducer {

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    private static final Gson gson = GsonFactory.generateGson();

    public void sendNotification(NotificationDto notificationDto) {
        try {
            String notificationJson = gson.toJson(notificationDto);
            rabbitTemplate.convertAndSend(topicExchange, routingKey, notificationJson);

            log.info(String.format("Notification successfully sent to publisher -> %s", notificationDto.getName()));
        } catch (Exception ex) {
            log.error(String.format("Incorrect notification data format -> %s, with exception -> %s", notificationDto, ex.getMessage()));
        }

    }
}
