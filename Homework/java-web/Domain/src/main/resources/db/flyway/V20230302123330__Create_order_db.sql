create table public."order"
(
    id                serial
        primary key,
    quantity_products integer not null,
    order_date        date    not null,
    id_user       serial
        constraint user_fkey
            references public.userdb,
    id_product        serial
        constraint product_fkey
            references public.product
);