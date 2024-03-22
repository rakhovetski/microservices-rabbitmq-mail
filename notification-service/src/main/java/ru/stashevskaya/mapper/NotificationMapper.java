package ru.stashevskaya.mapper;

import org.springframework.stereotype.Component;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.model.Notification;

@Component
public class NotificationMapper {

    public NotificationDto map(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getName(),
                notification.getDescription(),
                notification.getNotificationType(),
                notification.getCreatedAt(),
                notification.getEmail()
        );
    }
}
