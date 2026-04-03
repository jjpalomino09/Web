package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class CourseDto {
    public record CourseCreateRequest(
            String title,
            Boolean active,
            Long instructorId
    ) implements Serializable {}

    public record CourseResponse(
            Long id,
            String title,
            Boolean active,
            Long instructorId
    )  implements Serializable {}
}