package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Assessment;
import edu.unimagdalena.lms.repositories.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentRepository assessmentRepository;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public Assessment save(Assessment assessment) {
        if (assessment == null){
            throw  new IllegalArgumentException("La evaluacion no puede ser nula");
        }
        if (assessment.getStudent() == null){
            throw  new IllegalArgumentException("El estudiante es obligatorio");
        }
        if (assessment.getCourse() == null){
            throw  new IllegalArgumentException("El curso es obligatorio");
        }
        if (assessment.getType() == null || assessment.getType().isEmpty()){
            throw  new IllegalArgumentException("El tipo es obligatorio");
        }
        if (assessment.getScore() == null){
            throw  new IllegalArgumentException("La puntuacion es obligatorio");
        }
        if (assessment.getScore() < 0 || assessment.getScore() > 100){
            throw  new IllegalArgumentException("La puntuacion debe estar entre 0 y 100");
        }
        if (assessment.getTakenAt() == null){
            assessment.setTakenAt(Instant.now());
        }
        return assessmentRepository.save(assessment);
    }

    @Override
    public Assessment findById(Long id) {
        return assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));
    }

    @Override
    public List<Assessment> findAll() {
        return assessmentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!assessmentRepository.existsById(id)){
            throw  new RuntimeException("No se puede eliminar, no existe");
        }
        assessmentRepository.deleteById(id);
    }
}