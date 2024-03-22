create table if not exists notification
(
    id          bigserial primary key            not null,
    name        varchar(128)                     not null,
    description text                             not null,
    created_at  timestamp default now()          not null,
    email       varchar(256)                     not null,
    type        varchar(32)                      not null
);