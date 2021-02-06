create table account (
    id serial not null,
    username varchar(255) not null,
    password varchar(255) not null,

    constraint pk_account_id primary key (id),
    constraint uq_account_username unique (username)
);
