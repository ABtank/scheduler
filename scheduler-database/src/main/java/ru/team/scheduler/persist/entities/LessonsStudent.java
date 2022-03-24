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

    @Column(name = "is_confirmation_request_sent", nullable = false)
    private Boolean isСonfirmationRequestSent = false;

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

    public LessonsStudent(Integer id, Lesson lesson, User student, Boolean isAttend, Boolean isAccepted, Date dtCreate, Date dtModify) {
        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.isAttend = isAttend;
        this.isAccepted = isAccepted;
        this.dtCreate = dtCreate;
        this.dtModify = dtModify;
    }

    @Override
    public String toString() {
        return "LessonsStudent{" +
                "id=" + id +
                ", isAttend=" + isAttend +
                ", isAccepted=" + isAccepted +
                ", isСonfirmationRequestSent=" + isСonfirmationRequestSent +
                ", dtCreate=" + dtCreate +
                ", dtModify=" + dtModify +
                '}';
    }
}