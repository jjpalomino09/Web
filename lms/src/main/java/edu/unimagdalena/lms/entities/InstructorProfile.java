package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructor_profile")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phone;

    private String bio;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
}