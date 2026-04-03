package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.EnrollmentDto;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.entities.Student;

public class EnrollmentMapper {
    public static Enrollment toEntity(EnrollmentDto.EnrollmentCreateRequest req){
        return Enrollment.builder()
                .status(req.status())
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

    public static EnrollmentDto.EnrollmentResponse toResponse(Enrollment enrollment){
        return new EnrollmentDto.EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStatus(),
                enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
                enrollment.getCourse() != null ? enrollment.getCourse().getId() : null
        );
    }
}