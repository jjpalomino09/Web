package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment save(Enrollment enrollment);
    Enrollment findById(Long id);
    List<Enrollment> findAll();
    void deleteById(Long id);
}