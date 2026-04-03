package edu.unimagdalena.lms.services.mapper;

import edu.unimagdalena.lms.api.dto.InstructorDto;
import edu.unimagdalena.lms.api.dto.InstructorProfileDto;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.entities.InstructorProfile;

public class InstructorProfileMapper {
    public static InstructorProfile toEntity(InstructorProfileDto.InstructorProfileCreateRequest req){
        return InstructorProfile.builder()
                .phone(req.phone())
                .bio(req.bio())
                .instructor(
                        Instructor.builder()
                                .id(req.instructorId())
                                .build()
                )
                .build();
    }

    public static InstructorProfileDto.InstructorProfileResponse toResponse(InstructorProfile profile){
        return new InstructorProfileDto.InstructorProfileResponse(
                profile.getId(),
                profile.getPhone(),
                profile.getBio(),
                profile.getInstructor() != null ? profile.getInstructor().getId() : null
        );
    }
}