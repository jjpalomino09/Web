package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    void deleteById(Long id);
    List<Course> findByInstructorIdAndActiveTrue(Long instructorId);
}