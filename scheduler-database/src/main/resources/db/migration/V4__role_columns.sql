INSERT into users  ( email, phone, first_name, middle_name, last_name, password)
VALUES
( 'student1@mail.ru', '9-111-111-11-11', 'Иван', 'Петрович', 'Иванов', '$2y$10$S0bV2omeFkJG6DrFaZ65oO0MAcs/oETg.lYmpL8XkrvFXjBfuzxtm'),
( 'student2@mail.ru','9-222-111-11-11', 'Сергей', 'Сергеевич', 'Новиков', '$2y$10$U95eOxjOhkDzMB9dBh9dROypArcE0HhUEgDtfP80APw6jXlGg9qZ.'),
( 'teacher1@mail.ru', '9-333-111-11-11','Виктор', 'Петрович', 'Сидоров', '$2y$10$Zp8i.GEKAEYmgV5cHBxbuuEdQQkOmMd3IMqtqvxMN4Q6PszQl7wta'),
( 'teacher2@mail.ru', '9-444-111-11-11','Леонид', 'Петрович', 'Леонов', '$2y$10$8YCyedrqvRlh9IqFjoV/Aujrt0YVwxLMXgVb.wd2vLX9yPTBSi5l6');

INSERT into exercises (name, is_personal, duration, quantity, teacher_id, discipline_id )
VALUES
('Урок истории', true, 45, 1, 3, 9),
('Урок физики', true, 45, 1, 4, 1);

INSERT INTO users_roles (user_id, role_id)
VALUES
(2,1),
(3,1),
(4,2),
(5,2);

INSERT INTO lessons (name, link, exercise_id , dt_start)
VALUES
('История', 'no',  1 , '2022-02-13 09:15:00'),
('Физика', 'no', 2 , '2022-02-13 10:15:00');

INSERT INTO lessons_students (lesson_id, student_id)
VALUES
(1, 1),
(2, 2);

INSERT INTO teachers_students (teacher_id, student_id)
VALUES
(3,1),
(4,2);