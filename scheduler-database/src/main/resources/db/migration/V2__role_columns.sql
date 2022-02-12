alter table roles
    add description varchar(255) null;

INSERT INTO roles (name, description)
VALUES ('ROLE_STUDENT', 'Ученик');

UPDATE roles SET description = 'Преподаватель' WHERE name = 'ROLE_TEACHER';
UPDATE roles SET description = 'Пользователь' WHERE name = 'ROLE_USER';
UPDATE roles SET description = 'Администратор' WHERE name = 'ROLE_ADMIN';
