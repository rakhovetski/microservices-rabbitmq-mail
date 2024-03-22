package ru.stashevskaya.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stashevskaya.exception.NotificationException;
import ru.stashevskaya.util.ResponseEntityUtil;

@ControllerAdvice
public class NotificationExceptionHandler {

    @ExceptionHandler({
            NotificationException.class
    })
    public ResponseEntity<?> handleNotificationException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return ResponseEntityUtil.responseResultGenerate(httpStatus, exception.getMessage());
    }
}
