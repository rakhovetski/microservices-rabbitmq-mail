package ru.stashevskaya.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.stashevskaya.dto.NotificationCreateDto;
import ru.stashevskaya.dto.NotificationDto;
import ru.stashevskaya.model.NotificationType;
import ru.stashevskaya.service.NotificationService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create")
    public NotificationDto createNotification(@RequestBody NotificationCreateDto notificationCreateDto) {
        return notificationService.createNotification(notificationCreateDto);
    }

    @GetMapping("/{id}")
    public NotificationDto findNotificationById(@PathVariable Long id) {
        return notificationService.findNotificationById(id);
    }

    @GetMapping("")
    public List<NotificationDto> findAllNotificationsByFilters(
            @RequestParam(value = "type", required = false) List<NotificationType> notificationTypes,
            @RequestParam(value = "email", required = false) List<String> emails,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return notificationService.findNotificationsByFilters(notificationTypes, emails, page, size);
    }
}
