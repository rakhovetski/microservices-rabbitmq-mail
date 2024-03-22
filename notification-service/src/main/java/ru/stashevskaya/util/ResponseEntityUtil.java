package ru.stashevskaya.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.stashevskaya.dto.DefaultResponseDto;

import java.time.LocalDateTime;

public class ResponseEntityUtil {

    public static ResponseEntity<?> responseResultGenerate(HttpStatus status, String message) {
        DefaultResponseDto results = new DefaultResponseDto(message,
                status.toString(),
                LocalDateTime.now());

        return ResponseEntity.status(status).body(results);
    }
}