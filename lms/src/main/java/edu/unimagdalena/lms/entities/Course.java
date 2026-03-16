package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String status;

    private Boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @OrderBy("orderIndex ASC")
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments;

    @OneToMany(mappedBy = "course",  fetch = FetchType.LAZY)
    private Set<Assessment> assessments;
}
