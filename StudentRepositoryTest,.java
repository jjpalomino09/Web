package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
class StudentRepositoryTest {

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
    private StudentRepository studentRepository;

    @Test
    void testCreateStudent() {

        Student student = Student.builder()
                .email("test@email.com")
                .fullName("Test Student")
                .createdAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        assertNotNull(saved.getId());
    }

    @Test
    void testFindStudent() {

        Student student = Student.builder()
                .email("find@email.com")
                .fullName("Find Student")
                .createdAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        Optional<Student> found = studentRepository.findById(saved.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void testUpdateStudent() {

        Student student = Student.builder()
                .email("update@email.com")
                .fullName("Old Name")
                .createdAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        saved.setFullName("New Name");

        Student updated = studentRepository.save(saved);

        assertEquals("New Name", updated.getFullName());
    }

    @Test
    void testDeleteStudent() {

        Student student = Student.builder()
                .email("delete@email.com")
                .fullName("Delete Student")
                .createdAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        studentRepository.delete(saved);

        Optional<Student> deleted = studentRepository.findById(saved.getId());

        assertFalse(deleted.isPresent());
    }
}