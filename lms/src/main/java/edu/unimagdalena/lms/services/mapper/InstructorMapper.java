package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.InstructorDto;
import edu.unimagdalena.lms.entities.Instructor;

public class InstructorMapper {
    public static Instructor toEntity(InstructorDto.InstructorCreateRequest req){
        return Instructor.builder()
                .email(req.email())
                .fullName(req.fullName())
                .build();
    }

    public static InstructorDto.InstructorResponse toResponse(Instructor instructor){
        return new InstructorDto.InstructorResponse(
                instructor.getId(),
                instructor.getEmail(),
                instructor.getFullName()
        );
    }
}