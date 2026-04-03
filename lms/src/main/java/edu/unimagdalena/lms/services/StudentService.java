package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    Student findById(Long id);
    List<Student> findAll();
    void deleteById(Long id);
}