create table friends (
                         id bigserial not null,
                         first_user int8,
                         second_user int8,
                         primary key (id)
);
insert into friends (first_user, second_user)
values (2, 4);
insert into friends (first_user, second_user)
values (3, 4);
