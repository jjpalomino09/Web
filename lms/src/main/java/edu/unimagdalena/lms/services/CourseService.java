package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import java.util.List;

public interface CourseService {
    Course save(Course course);
    Course findById(Long id);
    List<Course> findAll();
    void deleteById(Long id);
    List<Course> findByInstructorIdAndActiveTrue(Long instructorId);
}