create table public.userdb
(
    id        serial
        primary key,

    email     text not null,
    password  text not null,
    name  text not null,
    age smallint not null ,
    balance real not null ,
    rating real not null,
    role      text not null,
    status    text not null,
    id_wallet int
        constraint wallet_fkey
            references public.wallet
);

