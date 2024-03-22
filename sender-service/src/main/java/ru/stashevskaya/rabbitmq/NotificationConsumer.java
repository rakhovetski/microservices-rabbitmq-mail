package ru.stashevskaya.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.senders.NotificationMailSender;
import ru.stashevskaya.util.GsonFactory;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationMailSender mailSender;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeNotification(String message) {
        try {
            NotificationDto dto = GsonFactory.generateGson().fromJson(message, NotificationDto.class);

            log.info(String.format("A message with the name and status has been received -> %s, %s", dto.getName(), dto.getNotificationType()));

            mailSender.sendNotification(dto);
        } catch (Exception ex) {
            log.error("Error while consuming the message " + ex.getMessage());
        }
    }
}