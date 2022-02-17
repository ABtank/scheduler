package ru.team.scheduler.persist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "calendar_days")
@Data
@NoArgsConstructor
public class CalendarDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name ="day_date")
    private Date dayDate;

    @Column (name = "is_holiday")
    private Boolean isHoliday;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weekday_id", nullable = false)
    private Weekday weekday;

}
