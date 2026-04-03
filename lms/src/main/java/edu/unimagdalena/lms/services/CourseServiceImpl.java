package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course){
        if (course == null){
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        if (course.getTitle() == null || course.getTitle().isEmpty()){
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (course.getInstructor() == null){
            throw new IllegalArgumentException("El instructor debe ser obligatorio");
        }
        if (course.getCreatedAt() == null){
            course.setCreatedAt(Instant.now());
        }

        course.setUpdatedAt(Instant.now());

        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    @Override
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        if (!courseRepository.existsById(id)){
            throw new RuntimeException("No se puede eliminar, no existe");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findByInstructorIdAndActiveTrue(Long instructorId) {
        return courseRepository.findByInstructorIdAndActiveTrue(instructorId);
    }
}