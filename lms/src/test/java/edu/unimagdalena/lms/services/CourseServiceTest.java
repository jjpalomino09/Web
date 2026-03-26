package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.repositories.CourseRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testCreateCourse() {
        Course course = Course.builder()
                .id(1L)
                .title("Mockito Course")
                .active(true)
                .build();

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course saved = courseService.createCourse(course);

        assertNotNull(saved);
        assertEquals("Mockito Course", saved.getTitle());

        verify(courseRepository).save(course);
    }

    @Test
    void testFindActiveCourses() {
        Course course = Course.builder()
                .id(1L)
                .title("Spring Boot")
                .active(true)
                .build();

        when(courseRepository.findByInstructorIdAndActiveTrue(1L))
                .thenReturn(List.of(course));

        List<Course> result = courseService.findActiveCourses(1L);

        assertFalse(result.isEmpty());
        assertEquals("Spring Boot", result.get(0).getTitle());

        verify(courseRepository)
                .findByInstructorIdAndActiveTrue(1L);
    }
}