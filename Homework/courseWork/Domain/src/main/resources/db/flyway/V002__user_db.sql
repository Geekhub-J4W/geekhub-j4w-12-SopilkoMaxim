create table public.user
(
    id        bigserial
        primary key,

    email     text not null,
    password  text not null,
    fullName  text not null,
    role      text not null,
    status    text not null,
    id_wallet int
        constraint wallet_fkey
            references public.wallet
);

