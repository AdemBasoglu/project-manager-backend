package be.intecbrussel.projectmanagerbackend.models.dto;

import java.time.LocalDate;

public record TaskDto(
        Long id,
        String name,
        String description,
        LocalDate createdDate
) {

}
