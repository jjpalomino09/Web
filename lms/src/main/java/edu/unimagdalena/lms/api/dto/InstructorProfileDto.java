package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class InstructorProfileDto {
    public record InstructorProfileCreateRequest(
            String phone,
            String bio,
            Long instructorId
    ) implements Serializable {}

    public record InstructorProfileResponse(
            Long id,
            String phone,
            String bio,
            Long instructorId
    )  implements Serializable {}
}