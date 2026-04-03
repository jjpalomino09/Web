package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor save(Instructor instructor) {
        if (instructor == null){
            throw new IllegalArgumentException("El instructor no puede ser nulo");
        }
        if (instructor.getEmail() == null || instructor.getEmail().isEmpty()){
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if(instructor.getFullName() == null || instructor.getFullName().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (instructor.getCreatedAt() == null){
            instructor.setCreatedAt(Instant.now());
        }

        instructor.setUpdatedAt(Instant.now());

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!instructorRepository.existsById(id)){
            throw new RuntimeException("No se puede eliminar, no existe");
        }
        instructorRepository.deleteById(id);
    }
}