package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson save(Lesson lesson) {
        if (lesson == null){
            throw new IllegalArgumentException("La leccion no puede ser nula");
        }
        if (lesson.getTitle() == null || lesson.getTitle().isEmpty()){
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (lesson.getCourse() == null){
            throw new IllegalArgumentException("El curso es obligatorio");
        }
        if (lesson.getOrderIndex() != null && lesson.getOrderIndex() < 0){
            throw new IllegalArgumentException("El orden no puede ser negativo");
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lectura no encontrada"));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if(!lessonRepository.existsById(id)){
            throw new RuntimeException("No se puede eliminar, no existe");
        }
        lessonRepository.deleteById(id);
    }
}