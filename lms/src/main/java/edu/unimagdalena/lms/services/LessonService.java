package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Lesson;
import java.util.List;
import java.util.Optional;
public interface LessonService {
    Lesson save(Lesson lesson);
    Optional<Lesson> findById(Long id);
    List<Lesson> findAll();
    void deleteById(Long id);
}