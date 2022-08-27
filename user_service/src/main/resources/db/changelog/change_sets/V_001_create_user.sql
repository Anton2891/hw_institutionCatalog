create table user_data
(
    id serial
        constraint user_data_pk
            primary key,
    name varchar(20) not null,
    surname varchar(20) not null,
    lastname varchar(20) not null,
    email varchar(100) not null,
    registration_date timestamp not null DEFAULT CURRENT_TIMESTAMP
);