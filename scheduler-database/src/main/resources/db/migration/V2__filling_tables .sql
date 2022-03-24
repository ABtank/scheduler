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
       ('История'),
       ('Скрипка'),
       ('Хор'),
       ('Фортепьяно'),
       ('Логопедические занаятия'),
       ('Солфеджио');


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
        '$2y$10$58mBHrd2opywMPa5hFOy9O5UoM/tRugP.VcxsZx7oA69RIy1ozVL6'),
       ('teacher3@mail.ru', '9-555-111-11-11', 'Анна', 'Петровна', 'Сударикова',
        '$2y$10$xAChARjEpMMTe/hpf2z.oO5NWHRpfHbUJZRAP57i/SuxdgmZrzDa2'),
       ('teacher4@mail.ru', '9-666-111-11-11', 'Евгения', 'Алексеевна', 'Новикова',
         '$2y$10$p597DddqDNRv5PK1UT/7Se6DK0gbuzLUA7TmiLQ2XDbogh8f7BkSe');


INSERT INTO teachers_disciplines (teacher_id, discipline_id)
VALUES (4, 2),
       (4, 1),
       (5,3),
       (5,4),
       (6,5),
       (6,6),
       (7,10),
       (7,11);

INSERT into exercises (name, is_personal, duration, quantity, teacher_id, discipline_id, is_validate, dt_modify)
VALUES
('Урок физики', true, 45, 1, 4, 1, false, '2022-03-05 07:53:00'),
('Урок химии', true, 45, 1, 4, 2, false, '2022-03-05 07:53:00'),
('Урок психологии', true, 30, 1, 5, 3, false, '2022-03-05 07:53:00'),
('Урок математики', true, 45, 1, 5, 4, false, '2022-03-05 07:53:00'),
('Урок литературы', true, 40, 1, 6, 5, false, '2022-03-05 07:53:00'),
('Урок английского языка', true, 45, 1, 6, 6, false, '2022-03-05 07:53:00'),
('Урок скрипки', true, 45, 1, 7, 10, false, '2022-03-05 07:53:00'),
('Урок хорового пения', true, 45, 1, 7, 10, false, '2022-03-05 07:53:00');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 4),
       (2, 4),
       (3, 4),
       (4, 2),
       (5, 2);

INSERT INTO lessons (name, link, exercise_id, dt_start)
VALUES ('Урок физики', 'no', 1, '2022-04-01 09:15:00'),
       ('Урок физики', 'no', 1, '2022-04-01 10:15:00');

INSERT INTO lessons_students (lesson_id, student_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);

INSERT INTO teachers_students (teacher_id, student_id)
VALUES (4, 1),
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
VALUES ('2022-04-01', 5, false),
       ('2022-04-02', 6, false),
       ('2022-04-03', 7, false),
       ('2022-04-04', 1, false),
       ('2022-04-05', 2, false),
       ('2022-04-06', 3, false),
       ('2022-04-07', 4, false),
       ('2022-04-08', 5, false),
       ('2022-04-09', 6, false),
       ('2022-04-10', 7, false),
       ('2022-04-11', 1, false),
       ('2022-04-12', 2, false),
       ('2022-04-13', 3, false),
       ('2022-04-14', 4, false),
       ('2022-04-15', 5, false),
       ('2022-04-16', 6, false),
       ('2022-04-17', 7, false),
       ('2022-04-18', 1, false),
       ('2022-04-19', 2, false),
       ('2022-04-20', 3, false),
       ('2022-04-21', 4, false),
       ('2022-04-22', 5, false),
       ('2022-04-23', 6, false),
       ('2022-04-24', 7, false),
       ('2022-04-25', 1, false),
       ('2022-04-26', 2, false),
       ('2022-04-27', 3, false),
       ('2022-04-28', 4, false),
       ('2022-04-29', 5, false),
       ('2022-04-30', 6, false);

INSERT INTO teacher_working_days (weekday_id, exercise_id, break_duration, lessons_quantity, t_start, t_end)
VALUES ('1', '1', '15', '2', '09:00:00', '11:00:00'),
       ('2', '1', '15', '2', '09:00:00', '11:00:00');