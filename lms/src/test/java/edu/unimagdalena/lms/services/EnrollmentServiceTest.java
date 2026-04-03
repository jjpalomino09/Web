package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repositories.EnrollmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @Test
    void shouldSaveEnrollment() {
        Enrollment enrollment = Enrollment.builder()
                .status("ACTIVE")
                .student(new Student())
                .course(new Course())
                .build();

        when(enrollmentRepository.save(any(Enrollment.class)))
                .thenReturn(enrollment);

        Enrollment result = enrollmentService.save(enrollment);

        assertNotNull(result);
        assertEquals("ACTIVE", result.getStatus());

        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void shouldThrowIfStudentIsNull() {
        Enrollment enrollment = Enrollment.builder()
                .status("ACTIVE")
                .course(new Course())
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            enrollmentService.save(enrollment);
        });

        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void shouldFindById(){
        Enrollment enrollment = Enrollment.builder()
                .id(1L)
                .status("ACTIVE")
                .student(new Student())
                .course(new Course())
                .build();

        when(enrollmentRepository.findById(1L))
                .thenReturn(Optional.of(enrollment));

        Enrollment result = enrollmentService.findById(1L);

        assertEquals("ACTIVE",  result.getStatus());
    }

    @Test
    void shouldThrowIfNotFound(){
        when(enrollmentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            enrollmentService.findById(1L);
        });
    }

    @Test
    void shouldDeleteEnrollment(){
        when(enrollmentRepository.existsById(1L)).thenReturn(true);

        enrollmentService.deleteById(1L);

        verify(enrollmentRepository).deleteById(1L);
    }
}