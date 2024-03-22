package ru.stashevskaya.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.stashevskaya.model.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT notification.* FROM notification WHERE notification.type IN (:types) AND notification.email IN (:emails);",
    countQuery ="SELECT count(notification.*) FROM notification WHERE notification.type IN (:types) AND notification.email IN (:emails);",
    nativeQuery = true)
    List<Notification> findAllByFilters(List<String> types, List<String> emails, Pageable pageable);
}
