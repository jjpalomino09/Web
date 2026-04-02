package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException("La inscripcion no puede ser nulo");
        }
        if (enrollment.getStudent() == null) {
            throw new IllegalArgumentException("El estudiante es obligatorio");
        }
        if (enrollment.getCourse() == null) {
            throw new IllegalArgumentException("El curso es obligatorio");
        }
        if (enrollment.getStatus() == null || enrollment.getStatus().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        if (enrollment.getEnrolledAt() == null) {
            enrollment.setEnrolledAt(Instant.now());
        }
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripcion no encontrada"));
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, no existe");
        }
        enrollmentRepository.deleteById(id);
    }
}
