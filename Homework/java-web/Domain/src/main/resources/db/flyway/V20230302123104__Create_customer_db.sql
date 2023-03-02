create table public.customer
(
    id   bigserial
        primary key,
    name text    not null,
    age  integer not null
);