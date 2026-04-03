package edu.unimagdalena.lms.api.dto;

import java.io.Serializable;

public class LessonDto {
    public record LessonCreateRequest(
            String title,
            Integer orderIndex,
            Long courseId
    ) implements Serializable {}

    public record LessonResponse(
            Long id,
            String title,
            Integer orderIndex,
            Long courseId
    )  implements Serializable {}
}