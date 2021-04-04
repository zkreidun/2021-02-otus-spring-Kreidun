create table authors
(
    id         bigserial,
    firstname  varchar(255),
    lastname   varchar(255),
    primary key (id)
);

create table genres
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);

create table books
(
    id        bigserial,
    title     varchar(255),
    author_id bigint references authors (id),
    genre_id  bigint references genres (id),
    primary key (id)
);

create table comments
(
    id      bigserial,
    text    varchar(8000),
    book_id bigint references books (id) on delete cascade,
    primary key (id)
);