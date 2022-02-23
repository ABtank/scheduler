package ru.team.scheduler.persist.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

    public Lesson(Integer id, String name, String link,  Exercise exercise, Instant dtStart) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.exercise = exercise;
        this.dtStart = dtStart;
    }
}