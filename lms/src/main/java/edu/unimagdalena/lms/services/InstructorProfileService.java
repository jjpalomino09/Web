package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.InstructorProfile;
import java.util.List;
import java.util.Optional;

public interface InstructorProfileService {
    InstructorProfile save(InstructorProfile profile);
    Optional<InstructorProfile> findById(Long id);
    List<InstructorProfile> findAll();
    void deleteById(Long id);
    Optional<InstructorProfile> findByInstructorId(Long instructorId);
}