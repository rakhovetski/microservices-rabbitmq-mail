package ru.stashevskaya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stashevskaya.model.NotificationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private NotificationType notificationType;

    @JsonProperty("email")
    @Email
    private String email;
}
