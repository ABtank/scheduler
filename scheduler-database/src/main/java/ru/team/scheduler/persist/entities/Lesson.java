package ru.team.scheduler.persist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "link", nullable = false, length = 256)
    private String link;

    @OneToMany(mappedBy = "lesson")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<LessonsStudent> lessonsStudents;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "dt_start", nullable = false)
    private Instant dtStart;

}