DROP TABLE IF EXISTS weekdays;
DROP TABLE IF EXISTS teacher_working_days;
DROP TABLE IF EXISTS calendar_days;


CREATE TABLE weekdays -- дни недели
(
    id          INT auto_increment primary key,
    name        VARCHAR(128)   NOT NULL, -- день недели
    CONSTRAINT  UK_day_name unique (name)
);


CREATE TABLE teacher_working_days -- дни и часы работы учителя
(
    id          INT auto_increment primary key,
    weekday_id  INT            NOT NULL,
    exercise_id INT            NOT NULL,
    time_start    time      NOT NULL, -- время начала рабочего дня
    time_end      time      NOT NULL, -- время окончания рабочего дня
    CONSTRAINT w_id
        FOREIGN KEY (weekday_id) REFERENCES weekdays (id),
    constraint ex_id
            FOREIGN KEY (exercise_id) REFERENCES exercises (id)
);


CREATE TABLE calendar_days -- даты и дни недели , праздники, в базе должен быть заполнен на фиксированный период (год, полугодие)
(    id          INT auto_increment primary key,
     day_date    date           NOT NULL,
     weekday_id  INT            NOT NULL,
     is_holiday  BOOLEAN       NOT NULL,
     constraint weekday_id
           FOREIGN KEY (weekday_id) REFERENCES weekdays (id)
);

