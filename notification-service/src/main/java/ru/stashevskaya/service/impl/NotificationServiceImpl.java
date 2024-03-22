package ru.stashevskaya.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stashevskaya.dto.NotificationCreateDto;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.exception.ErrorCode;
import ru.stashevskaya.exception.NotificationException;
import ru.stashevskaya.mapper.NotificationMapper;
import ru.stashevskaya.model.Notification;
import ru.stashevskaya.model.NotificationType;
import ru.stashevskaya.rabbitmq.NotificationProducer;
import ru.stashevskaya.repository.NotificationRepository;
import ru.stashevskaya.service.NotificationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public NotificationDto createNotification(NotificationCreateDto createDto) {
        Notification notification = Notification
                .builder()
                .name(createDto.getName())
                .description(createDto.getDescription())
                .notificationType(createDto.getNotificationType())
                .createdAt(LocalDateTime.now())
                .email(createDto.getEmail())
                .build();

        Notification result = notificationRepository.save(notification);

        log.info("The message has been saved to the database");

        NotificationDto notificationDto = notificationMapper.map(result);

        notificationProducer.sendNotification(notificationDto);

        log.info("The message has been sent to the rabbitmq consumer");

        return notificationDto;
    }

    @Override
    public NotificationDto findNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new NotificationException(ErrorCode.INCORRECT_NOTIFICATION_ID.getName())
        );

        log.info("The message has been found from the database");

        return notificationMapper.map(notification);
    }

    @Override
    public List<NotificationDto> findNotificationsByFilters(List<NotificationType> notificationTypes, List<String> emails,
    Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (notificationTypes == null) {
            notificationTypes = new ArrayList<>(Arrays.stream(NotificationType.values()).toList());
        }

        if (emails == null) {
            emails = new ArrayList<>(notificationRepository.findAll().stream().map(Notification::getEmail).toList());
        }

        List<String> types = notificationTypes.stream().map(Enum::toString).toList();
        List<Notification> notifications = notificationRepository.findAllByFilters(types, emails,
                pageRequest);

        return notifications.stream().map(notificationMapper::map).toList();
    }
}
