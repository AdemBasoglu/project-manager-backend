package be.intecbrussel.projectmanagerbackend.models.dto;

public record TaskDto(
        String name,
        String description
        // NOTE - Check idf the date is properly converted from frontend or don't use it.
        // LocalDate createdDate
) {
}
