package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.InstructorProfile;
import java.util.List;

public interface InstructorProfileService {
    InstructorProfile save(InstructorProfile profile);
    InstructorProfile findById(Long id);
    List<InstructorProfile> findAll();
    void deleteById(Long id);
    InstructorProfile findByInstructorId(Long instructorId);
}