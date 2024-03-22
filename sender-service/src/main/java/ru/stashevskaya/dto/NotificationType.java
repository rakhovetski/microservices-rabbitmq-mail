package ru.stashevskaya.dto;

import lombok.Getter;

@Getter
public enum NotificationType {

    INFO("info"),
    WARNING("warning"),
    ERROR("error"),
    FATAL("fatal");

    private final String name;

    NotificationType(String name) {
        this.name = name;
    }
}
