create table public.product
(
    id       bigserial
        primary key,
    name     text    not null,
    price    integer not null,
    quantity integer not null,
    rating   integer not null,
    id_image        bigserial
        constraint image_fkey
            references public.image
);