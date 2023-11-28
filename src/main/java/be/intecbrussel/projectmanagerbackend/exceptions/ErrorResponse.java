package be.intecbrussel.projectmanagerbackend.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int code,
        String path,
        String status,
        String message) {
}
