package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Assessment;
import java.util.List;
import java.util.Optional;

public interface AssessmentService {
    Assessment save(Assessment assessment);
    Assessment findById(Long id);
    List<Assessment> findAll();
    void deleteById(Long id);
}