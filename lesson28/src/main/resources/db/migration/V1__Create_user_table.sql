create table users (
                       id bigserial not null,
                       created_at timestamp,
                       password varchar(255),
                       role varchar(255),
                       username varchar(255),
                       primary key (id)
);

INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', 'password','USER', 'Andrey');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', 'password','USER', 'Yauhen');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', 'password','USER', 'Masha');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', 'password','USER', 'Glasha');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', 'admin','USER', 'admin');