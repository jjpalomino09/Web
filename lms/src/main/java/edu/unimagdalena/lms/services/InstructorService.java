package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Instructor;
import java.util.List;
import java.util.Optional;

public interface InstructorService {
    Instructor save(Instructor instructor);
    Instructor findById(Long id);
    List<Instructor> findAll();
    void deleteById(Long id);
}