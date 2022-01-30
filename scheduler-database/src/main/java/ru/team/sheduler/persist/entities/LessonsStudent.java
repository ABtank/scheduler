package ru.team.sheduler.persist.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "lessons_students")
@Data
@NoArgsConstructor
public class LessonsStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(name = "is_attend", nullable = false)
    private Boolean isAttend = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create", updatable = false)
    private Date dtCreate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_modify", nullable = false)
    private Date dtModify;

    public Date getDtCreate() {
        return dtCreate;
    }

    @PrePersist
    public void setDtCreate() {
        this.dtCreate = this.dtModify = new Date();
    }

    public Date getDtModify() {
        return dtModify;
    }

    @PreUpdate
    public void setDtModify() {
        this.dtModify = new Date();
    }
}