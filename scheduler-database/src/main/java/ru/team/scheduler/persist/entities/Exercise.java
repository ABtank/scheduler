package ru.team.scheduler.persist.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "is_personal", nullable = false)
    private Boolean isPersonal = false;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @OneToMany(mappedBy = "exercise")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Lesson> lessons;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_modify", nullable = false)
    private Date dtModify;

    public Exercise(Integer id, String name, Boolean isPersonal,
                    Integer duration, Integer quantity) {
        this.id = id;
        this.name = name;
        this.isPersonal = isPersonal;
        this.duration = duration;
        this.quantity = quantity;
    }

    public Exercise(String name, Integer duration, Boolean isPersonal, Integer quantity) {
        this.name = name;
        this.duration = duration;
        this.isPersonal = isPersonal;
        this.quantity = quantity;
    }
}