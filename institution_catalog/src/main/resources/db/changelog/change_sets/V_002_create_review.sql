create table review
(
    id            serial
        constraint review_pk
            primary key,
    institution_id bigint not null
        constraint review_institution_id_fk
            references institution,
    rating        int    not null,
    review   varchar(256)
);

