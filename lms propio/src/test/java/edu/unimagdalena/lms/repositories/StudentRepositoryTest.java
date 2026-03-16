package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldCreateStudent(){
        Student student = Student.builder()
                .email("victor@test.com")
                .fullName("Victor Diaz")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        assertNotNull(saved.getId());
    }

    @Test
    void shouldFindStudentById(){
        Student student = Student.builder()
                .email("test@test.com")
                .fullName("Test Student")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        Optional<Student> found = studentRepository.findById(saved.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void shouldUpdateStudent(){
        Student student = Student.builder()
                .email("old@test.com")
                .fullName("old name")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        saved.setFullName("New Name");

        Student updated = studentRepository.save(saved);

        assertEquals("New Name", updated.getFullName());
    }

    @Test
    void shouldDeleteStudent(){
        Student student = Student.builder()
                .email("delete@test.com")
                .fullName("Delete Student")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Student saved = studentRepository.save(student);

        studentRepository.delete(saved);

        Optional<Student> result = studentRepository.findById(saved.getId());

        assertFalse(result.isPresent());
    }
}
