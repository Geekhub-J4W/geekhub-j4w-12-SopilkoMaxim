create table public."order"
(
    id                serial
        primary key,
    quantiti_products integer not null,
    order_date        date    not null,
    id_customer       serial
        constraint customer_fkey
            references public.customer,
    id_product        serial
        constraint product_fkey
            references public.product
);