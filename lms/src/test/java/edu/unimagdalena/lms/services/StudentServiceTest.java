package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void shouldSaveStudentSuccessfully(){
        Student student = Student.builder()
                .email("test@email.com")
                .fullName("Victot Diaz")
                .build();

        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);

        Student result = studentService.save(student);

        assertNotNull(result);
        assertEquals("Victot Diaz", result.getFullName());

        verify(studentRepository).save(student);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull(){
        Student student = Student.builder()
                .fullName("Victot Diaz")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            studentService.save(student);
        });

        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldFindStudentById(){
        Student student = Student.builder()
                .id(1L)
                .email("test@email.com")
                .fullName("Victor Diaz")
                .build();

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student result = studentService.findById(1L);

        assertEquals("Victor Diaz", result.getFullName());

        verify(studentRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionIfStudentNotFound(){
        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            studentService.findById(1L);
        });
    }

    @Test
    void shouldDeleteStudent(){
        when(studentRepository.existsById(1L)).thenReturn(true);

        studentService.deleteById(1L);

        verify(studentRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingStudent(){
        when(studentRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            studentService.deleteById(1L);
        });
    }
}
