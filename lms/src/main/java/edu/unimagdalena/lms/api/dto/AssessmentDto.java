package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class AssessmentDto {
    public record AssessmentCreateRequest(
            String type,
            Integer score,
            Long studentId,
            Long courseId
    ) implements Serializable {}

    public record AssessmentResponse(
            Long id,
            String type,
            Integer score,
            Long studentId,
            Long courseId
    ) implements Serializable {}
}