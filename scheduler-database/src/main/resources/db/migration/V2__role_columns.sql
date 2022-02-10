alter table roles
    add description varchar(255) null;

INSERT INTO roles (name, description)
VALUES ('ROLE_STUDENT', 'Ученик');

UPDATE roles SET description = 'Преподаватель' WHERE name = 'ROLE_TEACHER';
UPDATE roles SET description = 'Пользователь' WHERE name = 'ROLE_USER';
UPDATE roles SET description = 'Администратор' WHERE name = 'ROLE_ADMIN';

INSERT INTO users(email, phone, first_name, middle_name, last_name, password) values ('111@mail.com', '11-11-11', 'иван', 'иванович', 'иванов', '111');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

