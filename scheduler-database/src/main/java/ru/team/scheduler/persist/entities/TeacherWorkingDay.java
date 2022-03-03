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

    @Column (name ="tStart")
    private Instant timeStart;

    @Column (name ="tEnd")
    private Instant timeEnd;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weekday_id", nullable = false)
    private Weekday weekday;

    public TeacherWorkingDay(Instant timeStart, Instant timeEnd) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
}
