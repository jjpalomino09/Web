package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.entities.InstructorProfile;
import edu.unimagdalena.lms.repositories.InstructorProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorProfileServiceTest {
    @Mock
    private InstructorProfileRepository profileRepository;

    @InjectMocks
    private InstructorProfileServiceImpl profileService;

    @Test
    void shouldSaveProfile() {
        InstructorProfile profile = InstructorProfile.builder()
                .phone("123456")
                .bio("bio")
                .instructor(new Instructor())
                .build();

        when(profileRepository.save(any(InstructorProfile.class)))
                .thenReturn(profile);

        InstructorProfile result = profileService.save(profile);

        assertNotNull(result);
        assertEquals("123456", result.getPhone());

        verify(profileRepository).save(profile);
    }

    @Test
    void shouldThrowIfInstructorIsNull() {
        InstructorProfile profile = InstructorProfile.builder()
                .phone("123456")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            profileService.save(profile);
        });

        verify(profileRepository, never()).save(any());
    }

    @Test
    void shouldFindByInstructorId() {
        InstructorProfile profile = InstructorProfile.builder()
                .id(1L)
                .instructor(new Instructor())
                .build();

        when(profileRepository.findByInstructorId(1L))
                .thenReturn(Optional.of(profile));
        InstructorProfile result = profileService.findByInstructorId(1L);

        assertNotNull(result);
    }

    @Test
    void shouldThrowIfProfileNotFound() {
        when(profileRepository.findByInstructorId(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            profileService.findByInstructorId(1L);
        });
    }

    @Test
    void shouldDeleteProfile() {
        when(profileRepository.existsById(1L)).thenReturn(true);

        profileService.deleteById(1L);

        verify(profileRepository).deleteById(1L);
    }
}