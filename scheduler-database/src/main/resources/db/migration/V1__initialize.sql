DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS positions_users;
DROP TABLE IF EXISTS teachers_disciplines;
DROP TABLE IF EXISTS seances_students;
DROP TABLE IF EXISTS teachers_students;
DROP TABLE IF EXISTS disciplines;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS seances;
DROP TABLE IF EXISTS exercises;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

-- пользовательские роли (ADMIN, USER)
CREATE TABLE roles
(
    id   int auto_increment primary key,
    name varchar(255) not null,
    CONSTRAINT UK_role_name unique (name)
);


CREATE TABLE users  -- not null только те поля, которые нужны при регистрации
(
    id         int auto_increment primary key,
    name       varchar(50)  not null,  -- логин
    phone      varchar(128) null,
    email      varchar(255) not null,
    firstName  varchar(50)  null,
    middleName varchar(50)  null,
    lastName   varchar(50)  null,
    password   varchar(128) not null,
    dt_create  timestamp    NOT NULL DEFAULT NOW(),
    dt_modify  timestamp    NOT NULL DEFAULT NOW()
);


-- статусы пользователей (Ученик, Преподаватель)
CREATE TABLE positions -- имя status более размытое понятие
(
    id   int auto_increment primary key,
    name varchar(50) NOT NULL,
    CONSTRAINT UK_position_name unique (name)
);

-- связь юзер позиция(должность) иначе препод не может быть учеником у другого препода
CREATE TABLE positions_users
(
    position_id int not null,
    user_id     int not null,
    primary key (position_id, user_id),
    CONSTRAINT FK_positions_users_user_id
        FOREIGN KEY (user_id) references users (id),
    CONSTRAINT FK_positions_users_position_id
        FOREIGN KEY (position_id) references positions (id)
);

CREATE TABLE users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    CONSTRAINT FK_users_roles_user_id
        FOREIGN KEY (user_id) references users (id),
    CONSTRAINT FK_users_roles_role_id
        FOREIGN KEY (role_id) references roles (id)
);

-- преподаваемые предметы
CREATE TABLE disciplines  -- subjects очень размытое понятие и боюсь получить конфликты имен Классов в Java
(
    id   int auto_increment primary key,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT UK_discipline_name UNIQUE (name)
);

CREATE TABLE teachers_disciplines
(
    teacher_id    INT NOT NULL,
    discipline_id INT NOT NULL,
    PRIMARY KEY (teacher_id, discipline_id),
    CONSTRAINT FK_teachers_disciplines_teacher_id
        FOREIGN KEY (teacher_id) REFERENCES users (id),
    CONSTRAINT FK_teachers_disciplines_discipline_id
        FOREIGN KEY (discipline_id) REFERENCES disciplines (id)
);
-- Суть, преподы создают уроки (exercise) и создают сеансы на данные уроки
-- Ученики подписываются на уроки типа беря билеты на соответствующие сеансы
-- короче аналогия с фильмами в кинотеатре

-- урок, пара, индивидуальное занятие
CREATE TABLE exercises
(
    id            int auto_increment primary key,
    name          VARCHAR(128) NOT NULL,             -- название урока, если пусто, то ставится название дисциплины
    link          VARCHAR(256) NOT NULL,             -- ссыль на вебинал
    is_personal   BOOLEAN      NOT NULL DEFAULT (1), -- персоналка или нет
    duration      INT          NOT NULL,             -- продолжительность урока
    quantity      INT          NOT NULL DEFAULT (1), -- если не персоналка, то указывается кол-во народу
    teacher_id    INT          NOT NULL,             -- учитель
    discipline_id INT          NOT NULL,             -- дисциплину
    CONSTRAINT FK_exercises_teacher_id
        FOREIGN KEY (teacher_id) REFERENCES users (id),
    CONSTRAINT FK_exercises_discipline_id
        FOREIGN KEY (discipline_id) REFERENCES disciplines (id)
);

-- сеансы урока, время начала и цена (необязательна, так что можно забить пока)
CREATE TABLE seances  -- из них и формируется рассписание
(
    id          int auto_increment primary key,
    exercise_id int            not null,
    price       decimal(19, 2) null,     -- цена
    dt_start    timestamp      not null, -- время начала урока(сеанса)
    CONSTRAINT seances_exercise_id
        FOREIGN KEY (exercise_id) REFERENCES exercises (id)
);

-- (типа билеты на урок)
CREATE TABLE seances_students -- можно обозвать (tickets)
(
    seance_id  int not null,
    student_id int not null,
    PRIMARY KEY (seance_id, student_id),
    CONSTRAINT tickets_seance_id
        FOREIGN KEY (seance_id) REFERENCES seances (id),
    constraint tickets_student_id
        FOREIGN KEY (student_id) REFERENCES users (id)
);

-- при взятии билета на урок, запись ученика в таблицу к учителю, если такого нет в таблице
CREATE TABLE teachers_students
(
    id         int auto_increment primary key,
    teacher_id int       not null,
    student_id int       not null,
    archive    Boolean            DEFAULT 0,     -- поместить ученика в архив (типа удалить)
    dt_create  timestamp NOT NULL DEFAULT NOW(), -- так, для аудита когда присоединился
    dt_modify  timestamp NOT NULL DEFAULT NOW(), -- когда ушел в архив или вернулся из него
    CONSTRAINT UK_teacher_id_student_id UNIQUE  (teacher_id, student_id),
    CONSTRAINT teachers_students_teacher_id
        FOREIGN KEY (teacher_id) REFERENCES users (id),
    constraint teachers_students_student_id
        FOREIGN KEY (student_id) REFERENCES users (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into positions (name)
values ('Учитель'),
       ('Студент');

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

