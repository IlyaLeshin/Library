package ru.otus.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_ratings")
public class BookRating {
    @Id
    @Column(name = "book_id")
    private long id;

    @OneToMany
    private Set<UserBookRating> userBookRatings;

    @OneToOne
    @MapsId
    @JoinColumn(name = "book_id")
    private Book book;

}
