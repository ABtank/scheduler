package ru.team.scheduler.persist.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessons_students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonsStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(name = "is_attend", nullable = false)
    private Boolean isAttend = false;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create", updatable = false)
    private Date dtCreate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_modify", nullable = false)
    private Date dtModify;

//    public Date getDtCreate() {
//        return dtCreate;
//    }

    @PrePersist
    public void setDtCreate() {
        this.dtCreate = this.dtModify = new Date();
    }

//    public Date getDtModify() {
//        return dtModify;
//    }

    @PreUpdate
    public void setDtModify() {
        this.dtModify = new Date();
    }
}