DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles ;
DROP TABLE IF EXISTS users;

create TABLE roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UK_role_name
        unique (name)
);

create TABLE users
(
    id       bigint auto_increment
        primary key,
    phone      varchar(128) null,
    email    varchar(255) null,
    name     varchar(32)  not null,
    password   varchar(128) not null,
    dt_create  DATETIME   NOT NULL DEFAULT NOW(),
    dt_modify  DATETIME   NOT NULL DEFAULT NOW()
);

create TABLE users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK_users_roles_user_id
        foreign key (user_id) references users (id),
    constraint FK_users_roles_role_id
        foreign key (role_id) references roles (id)
);
