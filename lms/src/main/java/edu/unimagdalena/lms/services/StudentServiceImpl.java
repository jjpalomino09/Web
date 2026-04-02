package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        if(student == null){
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (student.getFullName() == null || student.getFullName().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if(student.getCreatedAt() == null){
            student.setCreatedAt(Instant.now());
        }
        student.setUpdatedAt(Instant.now());
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    @Override
    public void deleteById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("No se puede eliminar, estudiante no existe");
        }
        studentRepository.deleteById(id);
    }
}