package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Instructor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void testCreateCourse() {

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("teacher@test.com")
                        .fullName("Teacher Test")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = Course.builder()
                .title("Spring Boot")
                .active(true)
                .instructor(instructor)
                .build();

        Course saved = courseRepository.save(course);

        assertNotNull(saved.getId());
    }

    @Test
    void testFindCourse() {

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("find@test.com")
                        .fullName("Find Teacher")
                        .createdAt(Instant.now())
                        .build()
        );

        Course saved = courseRepository.save(
                Course.builder()
                        .title("Java")
                        .active(true)
                        .instructor(instructor)
                        .build()
        );

        Optional<Course> found = courseRepository.findById(saved.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void testUpdateCourse() {

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("update@test.com")
                        .fullName("Update Teacher")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Old Title")
                        .active(true)
                        .instructor(instructor)
                        .build()
        );

        course.setTitle("New Title");

        Course updated = courseRepository.save(course);

        assertEquals("New Title", updated.getTitle());
    }

    @Test
    void testDeleteCourse() {

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("delete@test.com")
                        .fullName("Delete Teacher")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Database")
                        .active(true)
                        .instructor(instructor)
                        .build()
        );

        courseRepository.delete(course);

        Optional<Course> deleted = courseRepository.findById(course.getId());

        assertFalse(deleted.isPresent());
    }

    @Test
    void testFindByInstructorIdAndActiveTrue() {

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("query@test.com")
                        .fullName("Query Teacher")
                        .createdAt(Instant.now())
                        .build()
        );

        courseRepository.save(
                Course.builder()
                        .title("Testing")
                        .active(true)
                        .instructor(instructor)
                        .build()
        );

        List<Course> courses =
                courseRepository.findByInstructorIdAndActiveTrue(instructor.getId());

        assertFalse(courses.isEmpty());
    }
}