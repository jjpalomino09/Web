package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.InstructorProfile;
import edu.unimagdalena.lms.repositories.InstructorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorProfileServiceImpl implements InstructorProfileService {
    private final InstructorProfileRepository profileRepository;

    public InstructorProfileServiceImpl(InstructorProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public InstructorProfile save(InstructorProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("El perfil no puede ser nulo");
        }
        if (profile.getInstructor() == null) {
            throw new IllegalArgumentException("El instructor es obligatorio");
        }
        return profileRepository.save(profile);
    }

    @Override
    public InstructorProfile findById(Long id){
        return  profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));
    }

    @Override
    public List<InstructorProfile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        if (!profileRepository.existsById(id)){
            throw new RuntimeException("No se puede eliminar, no existe");
        }
        profileRepository.deleteById(id);
    }

    @Override
    public InstructorProfile findByInstructorId(Long instructorId) {
        return profileRepository.findByInstructorId(instructorId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado oara ese instructor"));
    }
}