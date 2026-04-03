package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.repositories.CourseRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void shouldSaveCourse() {
        Course course = Course.builder()
                .title("Mockito Course")
                .active(true)
                .instructor(new Instructor())
                .build();

        when(courseRepository.save(any(Course.class)))
                .thenReturn(course);

        Course result = courseService.save(course);

        assertNotNull(result);
        assertEquals("Mockito Course", result.getTitle());

        verify(courseRepository).save(course);
    }

    @Test
    void shouldThrowIfTitleIsEmpty() {
        Course course = Course.builder()
                .instructor(new Instructor())
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            courseService.save(course);
        });
    }

    @Test
    void shouldFindById() {
        Course course = Course.builder()
                .id(1L)
                .title("Spring")
                .instructor(new Instructor())
                .build();

        when(courseRepository.findById(1L))
                .thenReturn(Optional.of(course));

        Course result = courseService.findById(1L);

        assertEquals("Spring", result.getTitle());
    }

    @Test
    void shouldFindActiveCoursesByInstructor() {
        Course course = Course.builder()
                .title("Spring Boot")
                .active(true)
                .instructor(new Instructor())
                .build();

        when(courseRepository.findByInstructorIdAndActiveTrue(1L))
                .thenReturn(List.of(course));

        List<Course> result =
                courseService.findByInstructorIdAndActiveTrue(1L);

        assertFalse(result.isEmpty());
    }

    @Test
    void shouldDeleteCourse(){
        when(courseRepository.existsById(1L)).thenReturn(true);

        courseService.deleteById(1L);

        verify(courseRepository).deleteById(1L);
    }
}