package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.repositories.EnrollmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void testEnrollStudent() {
        Enrollment enrollment = Enrollment.builder()
                .id(1L)
                .status("ACTIVE")
                .build();

        when(enrollmentRepository.save(any(Enrollment.class)))
                .thenReturn(enrollment);

        Enrollment saved = enrollmentService.enrollStudent(enrollment);

        assertNotNull(saved);
        assertEquals("ACTIVE", saved.getStatus());

        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void testFindEnrollmentsByStudent() {
        Enrollment enrollment = Enrollment.builder()
                .id(1L)
                .status("ACTIVE")
                .build();

        when(enrollmentRepository.findByStudentId(1L))
                .thenReturn(List.of(enrollment));

        List<Enrollment> result =
                enrollmentService.findEnrollmentsByStudent(1L);

        assertFalse(result.isEmpty());
        assertEquals("ACTIVE", result.get(0).getStatus());

        verify(enrollmentRepository).findByStudentId(1L);
    }
}