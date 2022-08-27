create table user_role
(
    id              serial constraint user_role_pk primary key,
    id_user         bigint not null references user_data (id),
    id_role         bigint not null references role (id)
)