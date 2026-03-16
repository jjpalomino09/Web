package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testCreateStudent() {
        Student student = Student.builder()
                .id(1L)
                .email("mock@email.com")
                .fullName("Mock Student")
                .createdAt(Instant.now())
                .build();

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student saved = studentService.createStudent(student);

        assertNotNull(saved);
        assertEquals("Mock Student", saved.getFullName());

        verify(studentRepository).save(student);
    }

    @Test
    void testFindStudent() {
        Student student = Student.builder()
                .id(1L)
                .email("find@student.com")
                .fullName("Find Student")
                .createdAt(Instant.now())
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.findStudent(1L);

        assertTrue(result.isPresent());
        assertEquals("Find Student", result.get().getFullName());

        verify(studentRepository).findById(1L);
    }
}
