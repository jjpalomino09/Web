package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.LessonDto;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Lesson;

public class LessonMapper {
    public static Lesson toEntity(LessonDto.LessonCreateRequest req){
        return Lesson.builder()
                .title(req.title())
                .orderIndex(req.orderIndex())
                .course(
                        Course.builder()
                                .id(req.courseId())
                                .build()
                )
                .build();
    }

    public static LessonDto.LessonResponse toResponse(Lesson lesson){
        return new LessonDto.LessonResponse(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getOrderIndex(),
                lesson.getCourse() != null ? lesson.getCourse().getId() : null
        );
    }
}