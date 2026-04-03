package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class StudentDto {
    public record StudentCreateRequest(
            String email,
            String fullName
    ) implements Serializable {}

    public record StudentResponse(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}
}