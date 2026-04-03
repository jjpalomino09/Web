package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.StudentDto;
import edu.unimagdalena.lms.entities.Student;

public class StudentMapper {
    public static Student toEntity(StudentDto.StudentCreateRequest req) {
        return Student.builder()
                .email(req.email())
                .fullName(req.fullName())
                .build();
    }

    public static StudentDto.StudentResponse toResponse(Student student) {
        return new StudentDto.StudentResponse(
                student.getId(),
                student.getEmail(),
                student.getFullName()
        );
    }
}