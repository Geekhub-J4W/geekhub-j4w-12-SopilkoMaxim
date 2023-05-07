create table public.transfer
(
    id        bigserial
        primary key,

    coinName     text not null,
    amount  real not null,
    date  TIMESTAMP not null,
    price real not null ,
    operation text not null,
    user_id real not null

);

