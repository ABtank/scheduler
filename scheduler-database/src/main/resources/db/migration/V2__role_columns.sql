alter table roles
    add description varchar(255) null;

INSERT INTO roles (name, description)
VALUES ('ROLE_STUDENT', 'Ученик');

UPDATE roles SET description = 'Преподаватель' WHERE name = 'ROLE_TEACHER';
UPDATE roles SET description = 'Пользователь' WHERE name = 'ROLE_USER';
UPDATE roles SET description = 'Администратор' WHERE name = 'ROLE_ADMIN';

INSERT into users  ( email, phone, first_name, middle_name, last_name, password)
VALUES
( 'student1@mail.ru', '9-111-111-11-11', 'Иван', 'Петрович', 'Иванов', '1111'),
( 'student2@mail.ru','9-222-111-11-11', 'Сергей', 'Сергеевич', 'Новиков', '2222'),
( 'teacher1@mail.ru', '9-333-111-11-11','Виктор', 'Петрович', 'Сидоров', '3333'),
( 'teacher2@mail.ru', '9-444-111-11-11','Леонид', 'Петрович', 'Леонов', '4444');

INSERT into exercises (name, is_personal, duration, quantity, teacher_id, discipline_id )
VALUES
('Урок истории', true, 45, 1, 3, 9),
('Урок физики', true, 45, 1, 4, 1);

INSERT INTO users_roles (user_id, role_id)
VALUES
(1,1),
(2,1),
(3,2),
(4,2);

INSERT INTO lessons (name, exercise_id , dt_start)
VALUES
('История', 1 , '2022-02-13 09:15:00'),
('Физика', 2 , '2022-02-13 10:15:00');

INSERT INTO lessons_students (lesson_id, student_id)
VALUES
(1, 1),
(2, 2);

INSERT INTO teachers_students (teacher_id, student_id)
VALUES
(3,1),
(4,2);