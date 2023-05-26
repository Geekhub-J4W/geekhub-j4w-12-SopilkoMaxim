create table public.coin
(
    date   TIMESTAMP(0)
        primary key,

    Lido_Staked_Ether real    not null,
    Bitcoin real    not null,
    Ethereum real    not null,
    BNB real    not null,
    XRP real    not null,
    Polygon real    not null,
    Tether real    not null,
    USD_Coin real    not null,
    Cardano real    not null,
    Dogecoin real    not null

);

