package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.CourseDto;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Instructor;

public class CourseMapper {
    public static Course toEntity(CourseDto.CourseCreateRequest req){
        return Course.builder()
                .title(req.title())
                .active(req.active())
                .instructor(
                        Instructor.builder()
                                .id(req.instructorId())
                                .build()
                )
                .build();
    }

    public static CourseDto.CourseResponse toResponse(Course course){
        return new CourseDto.CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getActive(),
                course.getInstructor() != null ? course.getInstructor().getId() : null
        );
    }
}