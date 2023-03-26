create table public.product
(
    id       bigserial
        primary key,
    name     text    not null,
    price    integer not null,
    quantity integer not null,
    rating   integer not null,
    imgBytes bytea[]
);