package ru.otus.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.project.models.Book;
import ru.otus.project.models.TotalBookRating;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с общим рейтингом книг")
@DataJpaTest
class JpaTotalBookRatingRepositoryTest {

    private static final long FIRST_BOOK_ID = 1L;

    @Autowired
    private TotalBookRatingRepository totalBookRatingRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName("должен загружать рейтинг книги с bookId")
    @Test
    void shouldReturnCorrectById() {
        var optionalActualTotalBookRating = totalBookRatingRepository.findById(FIRST_BOOK_ID);
        var expectedTotalBookRating = testEntityManager.find(TotalBookRating.class, FIRST_BOOK_ID);

        assertThat(optionalActualTotalBookRating).isPresent().get()
                .isEqualTo(expectedTotalBookRating);
    }

    @DisplayName("должен сохранять рейтинг книги")
    @Test
    void shouldSaveUpdatedTotalBookRating() {
        var newTotalBookRating = new TotalBookRating();
        var testAverageRating = 4.2;
                newTotalBookRating.setAverageRating(testAverageRating);
        var testBook = testEntityManager.find(Book.class, FIRST_BOOK_ID);
        newTotalBookRating.setBook(testBook);

        var expectedTotalBookRating = totalBookRatingRepository.save(newTotalBookRating);

        assertThat(expectedTotalBookRating).isNotNull();

        var actualUserBookRating = testEntityManager.find(TotalBookRating.class, expectedTotalBookRating.getBookId());

        assertThat(actualUserBookRating)
                .isNotNull()
                .isEqualTo(expectedTotalBookRating);

        assertThat(actualUserBookRating)
                .matches(rating -> rating.getBookId()==(newTotalBookRating.getBookId()))
                .matches(rating -> rating.getAverageRating() == testAverageRating);
    }
}
