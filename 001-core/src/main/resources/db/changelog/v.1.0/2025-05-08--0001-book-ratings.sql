--liquibase formatted sql

--changeset IlyaLeshin:2025-05-08-001-book-ratings
create table if not exists user_book_ratings (
    user_id bigint not null references users(id) on delete cascade,
    book_id bigint not null references books(id) on delete cascade,
    rating integer not null,
    primary key (user_id, book_id)
);

create table if not exists total_book_ratings (
    book_id bigint not null unique references books(id) on delete cascade,
    average_rating real not null,
    primary key (book_id)
);

create table if not exists total_book_ratings_user_book_ratings (
    id bigint not null references total_book_ratings(book_id) on delete cascade,
    user_id bigint not null,
    book_id bigint not null,
    primary key (id, user_id, book_id),
    foreign key (user_id, book_id) references user_book_ratings(user_id, book_id) on delete cascade
);