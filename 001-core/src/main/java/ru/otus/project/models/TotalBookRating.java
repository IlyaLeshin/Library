package ru.otus.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "total_book_ratings")
public class TotalBookRating {
    @Id
    @Column(name = "id")
    private long bookId;

    @Column(name = "average_rating")
    private double averageRating;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany
    @JoinTable(name = "total_book_ratings_user_book_ratings", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = {@JoinColumn(name = "user_id"),
                    @JoinColumn(name = "book_id")})
    private Set<UserBookRating> userBookRatings;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "book_id")
    private Book book;
}
