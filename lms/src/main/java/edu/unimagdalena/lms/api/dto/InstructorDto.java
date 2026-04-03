package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class InstructorDto {
    public record InstructorCreateRequest(
            String email,
            String fullName
    ) implements Serializable {}

    public record InstructorResponse(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}
}