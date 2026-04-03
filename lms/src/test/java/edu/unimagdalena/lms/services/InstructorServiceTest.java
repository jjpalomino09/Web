package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.repositories.InstructorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {
    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    @Test
    void shouldSaveInstructor() {
        Instructor instructor = Instructor.builder()
                .email("test@email.com")
                .fullName("Victor Diaz")
                .build();

        when(instructorRepository.save(any(Instructor.class)))
                .thenReturn(instructor);

        Instructor result = instructorService.save(instructor);

        assertNotNull(result);
        assertEquals("Victor Diaz", result.getFullName());

        verify(instructorRepository).save(instructor);
    }

    @Test
    void shouldThrowIfEmailIsNull(){
        Instructor instructor = Instructor.builder()
                .fullName("Victor Diaz")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            instructorService.save(instructor);
        });

        verify(instructorRepository, never()).save(any());
    }

    @Test
    void shouldFindInstructorById() {
        Instructor instructor = Instructor.builder()
                .id(1L)
                .email("test@email.com")
                .fullName("Victor Diaz")
                .build();

        when(instructorRepository.findById(1L))
                .thenReturn(Optional.of(instructor));

        Instructor result = instructorService.findById(1L);

        assertEquals("Victor Diaz",  result.getFullName());
    }

    @Test
    void shouldThrowIfInstructorNotFound() {
        when(instructorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            instructorService.findById(1L);
        });
    }

    @Test
    void shouldDeleteInstructor(){
        when(instructorRepository.existsById(1L)).thenReturn(true);

        instructorService.deleteById(1L);

        verify(instructorRepository).existsById(1L);
    }
}