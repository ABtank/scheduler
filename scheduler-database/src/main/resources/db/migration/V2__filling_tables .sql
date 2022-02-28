INSERT INTO roles (name, description)
VALUES ('ROLE_USER', 'Пользователь'),
       ('ROLE_TEACHER', 'Преподаватель'),
       ('ROLE_ADMIN', 'Администратор'),
       ('ROLE_STUDENT', 'Ученик');

INSERT INTO disciplines (name)
VALUES ('Физика'),
       ('Химия'),
       ('Психология'),
       ('Математика'),
       ('Литература'),
       ('Английский язык'),
       ('Природоведение'),
       ('Информатика'),
       ('История');


INSERT into users (email, phone, first_name, middle_name, last_name, password)
VALUES ('student12@mail.com', '11-11-11', 'иван', 'иванович', 'иванов',
        '$2y$10$o31XeIhBgxyWecEaHgjRV.eNLWfETe/lHrpfYhztaaDVG6IQkeWf2'),
       ('student1@mail.ru', '9-111-111-11-11', 'Иван', 'Петрович', 'Иванов',
        '$2y$10$o31XeIhBgxyWecEaHgjRV.eNLWfETe/lHrpfYhztaaDVG6IQkeWf2'),
       ('student2@mail.ru', '9-222-111-11-11', 'Сергей', 'Сергеевич', 'Новиков',
        '$2y$10$7BCzrcWL/Je9a5RFftCisOp7s1D7LS63zPGvsrBvrqFOHm7zvapCK'),
       ('teacher1@mail.ru', '9-333-111-11-11', 'Виктор', 'Петрович', 'Сидоров',
        '$2y$10$FZSTTycr7kyL6BtV.A37tuW.TeD9q5Mn535Q.SAZnOOblQkFGL/bu'),
       ('teacher2@mail.ru', '9-444-111-11-11', 'Леонид', 'Петрович', 'Леонов',
        '$2y$10$58mBHrd2opywMPa5hFOy9O5UoM/tRugP.VcxsZx7oA69RIy1ozVL6');


INSERT INTO teachers_disciplines (teacher_id, discipline_id)
VALUES (1, 6),
       (4, 1);

INSERT into exercises (name, is_personal, duration, quantity, teacher_id, discipline_id)
VALUES ('Урок истории', true, 45, 1, 3, 9),
       ('Урок физики', true, 45, 1, 4, 1);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 4),
       (2, 4),
       (3, 4),
       (4, 2),
       (5, 2);

INSERT INTO lessons (name, link, exercise_id, dt_start)
VALUES ('История', 'no', 1, '2022-02-13 09:15:00'),
       ('Физика', 'no', 2, '2022-02-13 10:15:00');

INSERT INTO lessons_students (lesson_id, student_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO teachers_students (teacher_id, student_id)
VALUES (3, 1),
       (4, 2);



INSERT INTO weekdays (name)
VALUES ('Monday'),
       ('Tuesday'),
       ('Wednesday'),
       ('Thursday'),
       ('Friday'),
       ('Saturday'),
       ('Sunday');

INSERT INTO calendar_days (day_date, weekday_id, is_holiday)
VALUES ('2022-02-14', 1, false),
       ('2022-02-15', 2, false),
       ('2022-02-16', 3, false),
       ('2022-02-17', 4, false),
       ('2022-02-18', 5, false),
       ('2022-02-19', 6, false),
       ('2022-02-20', 7, false),
       ('2022-02-21', 1, false),
       ('2022-02-22', 2, false),
       ('2022-02-23', 3, false),
       ('2022-02-24', 4, false),
       ('2022-02-25', 5, false),
       ('2022-02-26', 6, false),
       ('2022-02-27', 7, false);

INSERT INTO teacher_working_days (weekday_id, exercise_id, t_start, t_end)
VALUES ('1', '1', '09:00:00', '11:00:00'),
       ('2', '1', '09:00:00', '11:00:00');