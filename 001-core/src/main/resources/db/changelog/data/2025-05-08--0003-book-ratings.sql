--liquibase formatted sql

--changeset IlyaLeshin:2025-05-08-003-book-ratings
insert into user_book_ratings(user_id, book_id, rating)
values (1,1,4), (1,2,5), (2,1,4), (2,2,5);

insert into total_book_ratings(book_id, average_rating)
values (1,4.0), (2,5.0), (3,0.0);

insert into total_book_ratings_user_book_ratings(id, user_id, book_id)
values (1,1,1), (1,2,1), (2,1,2), (3,1,3);