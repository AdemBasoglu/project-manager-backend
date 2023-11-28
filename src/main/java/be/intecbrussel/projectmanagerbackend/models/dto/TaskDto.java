package be.intecbrussel.projectmanagerbackend.models.dto;

import java.time.LocalDate;

public record TaskDto(
        Long id,
        String name,
        String description
        // NOTE - CHeck idf the date is properly converted from frontend or don't use it.
        // LocalDate createdDate
) {
}
