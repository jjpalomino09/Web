package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Assessment;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repositories.AssessmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
    @Mock
    private AssessmentRepository assessmentRepository;

    @InjectMocks
    private AssessmentServiceImpl assessmentService;

    @Test
    void shouldSaveAssessment() {
        Assessment assessment = Assessment.builder()
                .type("EXAM")
                .score(90)
                .student(new Student())
                .course(new Course())
                .build();

        when(assessmentRepository.save(any(Assessment.class)))
                .thenReturn(assessment);

        Assessment result = assessmentService.save(assessment);

        assertNotNull(result);
        assertEquals(90, result.getScore());

        verify(assessmentRepository).save(assessment);
    }

    @Test
    void shouldThrowIfScoreInvalid(){
        Assessment assessment = Assessment.builder()
                .type("EXAM")
                .score(150)
                .student(new Student())
                .course(new Course())
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            assessmentService.save(assessment);
        });

        verify(assessmentRepository, never()).save(any());
    }

    @Test
    void shouldFindById(){
        Assessment assessment = Assessment.builder()
                .id(1L)
                .type("QUIZ")
                .score(80)
                .student(new Student())
                .course(new Course())
                .build();

        when(assessmentRepository.findById(1L))
                .thenReturn(Optional.of(assessment));

        Assessment result = assessmentService.findById(1L);

        assertEquals("QUIZ", result.getType());
    }

    @Test
    void shouldThrowIfNotFound(){
        when(assessmentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            assessmentService.findById(1L);
        });
    }

    @Test
    void shouldDeleteAssessment(){
        when(assessmentRepository.existsById(1L)).thenReturn(true);

        assessmentService.deleteById(1L);

        verify(assessmentRepository).deleteById(1L);
    }
}