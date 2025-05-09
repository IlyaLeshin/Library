package ru.otus.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserFavoriteBookId {

    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "book_id")
    private Long bookId;
}
