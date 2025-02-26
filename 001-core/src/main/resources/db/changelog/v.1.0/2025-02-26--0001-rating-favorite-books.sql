--liquibase formatted sql

--changeset IlyaLeshin:2025-02-26-001-rating-favorite-books
create table if not exists user_book_ratings (
    id bigserial not null unique,
    rating double not null,
    book_id bigint references books(id) on delete cascade,
    user_id bigint references users(id) on delete cascade,
    primary key (id)
);

create table if not exists book_ratings (
    book_id bigint references books(id) on delete cascade,
    user_book_rating_id bigint references user_book_ratings(id),
    primary key (book_id)
);

create table if not exists favorite_books (
    id bigserial not null unique,
    user_id bigint references users(id) on delete cascade,
    primary key (id)
);

create table if not exists favorite_books_books (
    favorite_book_id bigint references favorite_books(id) on delete cascade,
    book_id bigint references books(id) on delete cascade,
    primary key (favorite_book_id, book_id)
);
