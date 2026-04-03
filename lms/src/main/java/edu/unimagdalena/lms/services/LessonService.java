package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Lesson;
import java.util.List;

public interface LessonService {
    Lesson save(Lesson lesson);
    Lesson findById(Long id);
    List<Lesson> findAll();
    void deleteById(Long id);
}