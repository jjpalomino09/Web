package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.entities.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
class EnrollmentRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void testCreateEnrollment() {

        Student student = studentRepository.save(
                Student.builder()
                        .email("student@test.com")
                        .fullName("Student Test")
                        .createdAt(Instant.now())
                        .build()
        );

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("inst@test.com")
                        .fullName("Instructor Test")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Spring Boot")
                        .instructor(instructor)
                        .build()
        );

        Enrollment enrollment = Enrollment.builder()
                .status("ACTIVE")
                .enrolledAt(Instant.now())
                .student(student)
                .course(course)
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        assertNotNull(saved.getId());
    }

    @Test
    void testFindEnrollmentById() {

        Student student = studentRepository.save(
                Student.builder()
                        .email("find@student.com")
                        .fullName("Find Student")
                        .createdAt(Instant.now())
                        .build()
        );

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("find@instructor.com")
                        .fullName("Find Instructor")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Java")
                        .instructor(instructor)
                        .build()
        );

        Enrollment enrollment = enrollmentRepository.save(
                Enrollment.builder()
                        .status("ACTIVE")
                        .student(student)
                        .course(course)
                        .build()
        );

        Optional<Enrollment> found =
                enrollmentRepository.findById(enrollment.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void testUpdateEnrollment() {

        Student student = studentRepository.save(
                Student.builder()
                        .email("update@student.com")
                        .fullName("Update Student")
                        .createdAt(Instant.now())
                        .build()
        );

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("updated@instructor.com")
                        .fullName("Updated Instructor")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Spring")
                        .instructor(instructor)
                        .build()
        );

        Enrollment enrollment = enrollmentRepository.save(
                Enrollment.builder()
                        .status("ACTIVE")
                        .student(student)
                        .course(course)
                        .build()
        );

        enrollment.setStatus("COMPLETED");

        Enrollment updated = enrollmentRepository.save(enrollment);

        assertEquals("COMPLETED", updated.getStatus());
    }

    @Test
    void testDeleteEnrollment() {

        Student student = studentRepository.save(
                Student.builder()
                        .email("delete@student.com")
                        .fullName("Delete Student")
                        .createdAt(Instant.now())
                        .build()
        );

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("delete@instructor.com")
                        .fullName("Delete Instructor")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Database")
                        .instructor(instructor)
                        .build()
        );

        Enrollment enrollment = enrollmentRepository.save(
                Enrollment.builder()
                        .status("ACTIVE")
                        .student(student)
                        .course(course)
                        .build()
        );

        enrollmentRepository.delete(enrollment);

        Optional<Enrollment> deleted =
                enrollmentRepository.findById(enrollment.getId());

        assertFalse(deleted.isPresent());
    }

    @Test
    void testFindByStudentId() {

        Student student = studentRepository.save(
                Student.builder()
                        .email("query@student.com")
                        .fullName("Query Student")
                        .createdAt(Instant.now())
                        .build()
        );

        Instructor instructor = instructorRepository.save(
                Instructor.builder()
                        .email("query@instructor.com")
                        .fullName("Query Instructor")
                        .createdAt(Instant.now())
                        .build()
        );

        Course course = courseRepository.save(
                Course.builder()
                        .title("Testing")
                        .instructor(instructor)
                        .build()
        );

        enrollmentRepository.save(
                Enrollment.builder()
                        .status("ACTIVE")
                        .student(student)
                        .course(course)
                        .build()
        );

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(student.getId());

        assertFalse(enrollments.isEmpty());
    }
}