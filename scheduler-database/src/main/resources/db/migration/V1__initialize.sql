DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS teachers_disciplines;
DROP TABLE IF EXISTS lessons_students;
DROP TABLE IF EXISTS teachers_students;
DROP TABLE IF EXISTS disciplines;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS exercises;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS weekdays;
DROP TABLE IF EXISTS teacher_working_days;
DROP TABLE IF EXISTS calendar_days;

-- пользовательские роли (ADMIN, USER,TEACHER)
CREATE TABLE roles
(
    id          int auto_increment primary key,
    name        varchar(255) not null,
    description varchar(255) null,
    CONSTRAINT UK_role_name unique (name)
);


CREATE TABLE users -- not null только те поля, которые нужны при регистрации
(
    id          int auto_increment primary key,
    email       varchar(255) not null,
    phone       varchar(128) null,
    first_name  varchar(50)  null,
    middle_name varchar(50)  null,
    last_name   varchar(50)  null,
    password    varchar(128) not null,
    dt_create   timestamp    NOT NULL DEFAULT NOW(),
    dt_modify   timestamp    NOT NULL DEFAULT NOW(),
    CONSTRAINT UK_user_email UNIQUE (email)
);



CREATE TABLE users_roles
(
    user_id int not null,
    role_id int not null,
    CONSTRAINT UC_user_role UNIQUE (user_id, role_id),
    primary key (user_id, role_id),
    CONSTRAINT FK_users_roles_user_id
        FOREIGN KEY (user_id) references users (id),
    CONSTRAINT FK_users_roles_role_id
        FOREIGN KEY (role_id) references roles (id)
);


-- преподаваемые предметы
CREATE TABLE disciplines -- subjects очень размытое понятие и боюсь получить конфликты имен Классов в Java
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
    is_personal   BOOLEAN      NOT NULL DEFAULT (1), -- персоналка или нет
    duration      INT          NOT NULL,             -- продолжительность урока
    quantity      INT          NOT NULL DEFAULT (1), -- если не персоналка, то указывается кол-во народу
    teacher_id    INT          NOT NULL,             -- учитель
    discipline_id INT          NOT NULL,             -- дисциплина
    is_validate   BOOLEAN      NOT NULL DEFAULT (0), -- необходимость подтверждения учениками присутствия на занятии
    CONSTRAINT FK_exercises_teacher_id
        FOREIGN KEY (teacher_id) REFERENCES users (id),
    CONSTRAINT FK_exercises_discipline_id
        FOREIGN KEY (discipline_id) REFERENCES disciplines (id)
);

-- сеансы урока, время начала и цена (необязательна, так что можно забить пока)
CREATE TABLE lessons -- из них и формируется рассписание
(
    id          INT auto_increment primary key,
    name        VARCHAR(128) NOT NULL, -- название сеанса, если пусто, то ставится название урока
    link        VARCHAR(256) NOT NULL, -- ссыль на вебинар
    exercise_id INT          NOT NULL,
--    price       decimal(19, 2) NULL,     -- цена (ну а вдруг)
    dt_start    timestamp    NOT NULL, -- время начала урока(сеанса)
    CONSTRAINT lessons_exercise_id
        FOREIGN KEY (exercise_id) REFERENCES exercises (id)
);

-- (типа билеты на урок)
CREATE TABLE lessons_students -- можно обозвать (tickets)
(
    id         int auto_increment primary key,
    lesson_id  int       not null,
    student_id int       not null,
    is_attend  BOOLEAN   not null DEFAULT (1), -- присутствовал или нет. Изначально ставиться присутствовал. Если поздно отменил, то прогул = 0. Если вовремя отменил запись, то удаление записи.
    is_accepted BOOLEAN  not null DEFAULT (0), -- подтверждение присутствия на занятии (ставится 1, если учитель требует обязательного подтверждения по предмету)
    dt_create  timestamp NOT NULL DEFAULT NOW(),
    dt_modify  timestamp NOT NULL DEFAULT NOW(),
    CONSTRAINT UK_lesson_id_student_id UNIQUE (lesson_id, student_id),
    CONSTRAINT tickets_lesson_id
        FOREIGN KEY (lesson_id) REFERENCES lessons (id),
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
    CONSTRAINT UK_teacher_id_student_id UNIQUE (teacher_id, student_id),
    CONSTRAINT teachers_students_teacher_id
        FOREIGN KEY (teacher_id) REFERENCES users (id),
    constraint teachers_students_student_id
        FOREIGN KEY (student_id) REFERENCES users (id)
);



CREATE TABLE weekdays -- дни недели
(
    id   INT auto_increment primary key,
    name VARCHAR(128) NOT NULL, -- день недели
    CONSTRAINT UK_day_name unique (name)
);


CREATE TABLE teacher_working_days -- дни и часы работы учителя
(
    id          INT auto_increment primary key,
    weekday_id  INT  NOT NULL,
    exercise_id INT  NOT NULL,
    time_start  time NOT NULL, -- время начала рабочего дня
    time_end    time NOT NULL, -- время окончания рабочего дня
    CONSTRAINT w_id
        FOREIGN KEY (weekday_id) REFERENCES weekdays (id),
    constraint ex_id
        FOREIGN KEY (exercise_id) REFERENCES exercises (id)
);


CREATE TABLE calendar_days -- даты и дни недели , праздники, в базе должен быть заполнен на фиксированный период (год, полугодие)
(
    id         INT auto_increment primary key,
    day_date   date    NOT NULL,
    weekday_id INT     NOT NULL,
    is_holiday BOOLEAN NOT NULL,
    constraint weekday_id
        FOREIGN KEY (weekday_id) REFERENCES weekdays (id)
);




