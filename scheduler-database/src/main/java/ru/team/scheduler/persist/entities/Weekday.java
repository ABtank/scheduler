package ru.team.scheduler.persist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weekdays")
@Data
@NoArgsConstructor
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "weekday")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<CalendarDay> calendarDays;

    @OneToMany(mappedBy = "weekday")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<TeacherWorkingDay> teacherWorkingDays;
}
