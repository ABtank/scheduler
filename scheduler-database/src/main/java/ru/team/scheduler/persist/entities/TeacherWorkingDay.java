package ru.team.scheduler.persist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "teacher_working_days")
@Data
@NoArgsConstructor
public class TeacherWorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Instant time_start;

    @Column
    private Instant time_end;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weekday_id", nullable = false)
    private Weekday weekday;

    public TeacherWorkingDay(Instant time_start, Instant time_end) {
        this.time_start = time_start;
        this.time_end = time_end;
    }
}
