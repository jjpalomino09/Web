package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.repositories.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {
    @Mock
    LessonRepository lessonRepository;

    @InjectMocks
    LessonServiceImpl lessonService;

    @Test
    void shouldSaveLesson() {
        Lesson lesson = Lesson.builder()
                .title("Intro")
                .orderIndex(1)
                .course(new Course())
                .build();

        when(lessonRepository.save(any(Lesson.class)))
                .thenReturn(lesson);

        Lesson result = lessonService.save(lesson);

        assertNotNull(result);
        assertEquals("Intro",  result.getTitle());

        verify(lessonRepository).save(lesson);
    }

    @Test
    void shouldThrowIfTitleIsNull() {
        Lesson lesson = Lesson.builder()
                .course(new Course())
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            lessonService.save(lesson);
        });

        verify(lessonRepository, never()).save(any());
    }

    @Test
    void shouldFindLessonById() {
        Lesson lesson = Lesson.builder()
                .id(1L)
                .title("Intro")
                .course(new Course())
                .build();

        when(lessonRepository.findById(1L))
                .thenReturn(Optional.of(lesson));

        Lesson result = lessonService.findById(1L);

        assertEquals("Intro", result.getTitle());
    }

    @Test
    void shouldThrowIfNotFound(){
        when(lessonRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            lessonService.findById(1L);
        });
    }

    @Test
    void shouldDeleteLesson() {
        when(lessonRepository.existsById(1L)).thenReturn(true);

        lessonService.deleteById(1L);

        verify(lessonRepository).deleteById(1L);
    }
}