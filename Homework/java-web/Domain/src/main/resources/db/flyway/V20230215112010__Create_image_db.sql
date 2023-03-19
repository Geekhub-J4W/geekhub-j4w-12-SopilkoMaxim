create table public."image"
(
    id bigserial primary key,
    name text  NOT NULL,
    originalFileName text NOT NULL,
    size bigint NOT NULL,
    contentType text NOT NULL,
    bytes bytea

);