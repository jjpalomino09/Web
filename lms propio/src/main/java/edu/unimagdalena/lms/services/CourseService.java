package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findActiveCourses(Long instructorId) {
        return courseRepository.findByInstructorIdAndActiveTrue(instructorId);
    }
}