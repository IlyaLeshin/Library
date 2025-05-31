--liquibase formatted sql

--changeset IlyaLeshin:2025-05-08-004-favorite-books

create table if not exists user_favorite_books (
    user_id bigint references users(id) on delete cascade,
    book_id bigint references books(id) on delete cascade,
    primary key (user_id, book_id)
);