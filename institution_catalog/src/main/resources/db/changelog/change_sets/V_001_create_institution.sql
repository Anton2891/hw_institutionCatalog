create table institution
(
    id serial
        constraint institution_pk
            primary key,
    name varchar(20),
    address varchar(100),
    description VARCHAR(500)
);