package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class EnrollmentDto {
    public record EnrollmentCreateRequest(
            String status,
            Long studentId,
            Long courseId
    ) implements Serializable {}

    public record EnrollmentResponse(
            Long id,
            String status,
            Long studentId,
            Long courseId
    )  implements Serializable {}
}