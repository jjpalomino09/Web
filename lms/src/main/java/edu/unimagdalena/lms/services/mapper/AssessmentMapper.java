package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.AssessmentDto;
import edu.unimagdalena.lms.entities.Assessment;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Student;

public class AssessmentMapper {
    public static Assessment toEntity(AssessmentDto.AssessmentCreateRequest req){
        return Assessment.builder()
                .type(req.type())
                .score(req.score())
                .student(
                        Student.builder()
                                .id(req.studentId())
                                .build()
                )
                .course(
                        Course.builder()
                                .id(req.courseId())
                                .build()
                )
                .build();
    }

    public static AssessmentDto.AssessmentResponse toResponse(Assessment assessment){
        return new AssessmentDto.AssessmentResponse(
                assessment.getId(),
                assessment.getType(),
                assessment.getScore(),
                assessment.getStudent() != null ? assessment.getStudent().getId() : null,
                assessment.getCourse() != null ? assessment.getCourse().getId() : null
        );
    }
}