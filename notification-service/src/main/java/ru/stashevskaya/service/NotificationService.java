package ru.stashevskaya.service;

import ru.stashevskaya.dto.NotificationCreateDto;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.model.NotificationType;

import java.util.List;

public interface NotificationService {

    NotificationDto createNotification(NotificationCreateDto createDto);

    NotificationDto findNotificationById(Long id);

    List<NotificationDto> findNotificationsByFilters(List<NotificationType> notificationTypes, List<String> email,
                                                     Integer page, Integer size);
}
