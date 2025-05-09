--liquibase formatted sql

--changeset IlyaLeshin:2025-05-08-002-favorite-books
insert into user_favorite_books(user_id, book_id)
values (1,1), (1,2);