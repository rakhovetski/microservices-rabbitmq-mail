package ru.stashevskaya.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INCORRECT_NOTIFICATION_ID("Incorrect notification id");

    private final String name;

    ErrorCode(String name) {
        this.name = name;
    }
}
