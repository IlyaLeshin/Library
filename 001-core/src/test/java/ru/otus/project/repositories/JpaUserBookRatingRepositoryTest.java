package ru.otus.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.project.models.*;
import ru.otus.project.security.models.User;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с пользовательским рейтингом книг")
@DataJpaTest
class JpaUserBookRatingRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_USER_RATING_BOOKS_BY_USER = 2;
    private static final int EXPECTED_NUMBER_OF_USER_RATING_BOOKS_BY_BOOK = 1;
    private static final long FIRST_USER_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final UserBookRatingId USER_BOOK_RATING_ID = new UserBookRatingId(FIRST_USER_ID, FIRST_BOOK_ID);


    @Autowired
    private UserBookRatingRepository userBookRatingRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName("должен загружать рейтинг по userId и bookId")
    @Test
    void shouldReturnCorrectUserBookRatingById() {
        var optionalActualUserBookRating = userBookRatingRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID);
        var expectedUserBookRating = testEntityManager.find(UserBookRating.class, USER_BOOK_RATING_ID);

        assertThat(optionalActualUserBookRating).isPresent().get()
                .isEqualTo(expectedUserBookRating);
    }

    @DisplayName("должен загружать список рейтингов всех книг от одного пользователя")
    @Test
    void shouldReturnCorrectUserBookRatingListByUserId() {
        var actualUserBookRatings = userBookRatingRepository.findAllByUserId(FIRST_USER_ID);

        assertThat(actualUserBookRatings).isNotNull().hasSize(EXPECTED_NUMBER_OF_USER_RATING_BOOKS_BY_USER)
                .allMatch(rating -> rating.getUserBookRatingId() != null)
                .allMatch(rating -> rating.getUser() != null)
                .allMatch(rating -> rating.getBook() != null);
    }

    @DisplayName("должен загружать список рейтингов книги от всех пользователей")
    @Test
    void shouldReturnCorrectUserBookRatingListByBookId() {
        var actualUserBookRatings = userBookRatingRepository.findAllByBookId(FIRST_BOOK_ID);

        assertThat(actualUserBookRatings).isNotNull().hasSize(EXPECTED_NUMBER_OF_USER_RATING_BOOKS_BY_BOOK)
                .allMatch(rating -> rating.getUserBookRatingId() != null)
                .allMatch(rating -> rating.getUser() != null)
                .allMatch(rating -> rating.getBook() != null);
    }


    @DisplayName("должен сохранять пользовательский рейтинг книги")
    @Test
    void shouldSaveUpdatedUserBookRating() {
        var newUserBookRating = new UserBookRating();
        newUserBookRating.setUserBookRatingId(USER_BOOK_RATING_ID);
        var testRating = 1;
        newUserBookRating.setRating(testRating);
        var testUser = testEntityManager.find(User.class, FIRST_USER_ID);
        newUserBookRating.setUser(testUser);
        var testBook = testEntityManager.find(Book.class, FIRST_BOOK_ID);
        newUserBookRating.setBook(testBook);

        var expectedUserBookRating = userBookRatingRepository.save(newUserBookRating);

        assertThat(expectedUserBookRating).isNotNull();

        var actualUserBookRating = testEntityManager.find(UserBookRating.class, expectedUserBookRating.getUserBookRatingId());

        assertThat(actualUserBookRating)
                .isNotNull()
                .isEqualTo(expectedUserBookRating);

        assertThat(actualUserBookRating)
                .matches(rating -> rating.getUserBookRatingId().getBookId().equals(newUserBookRating.getUserBookRatingId().getBookId()))
                .matches(rating -> rating.getRating() == 1);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteUserBookRating() {
        var userBookRating = userBookRatingRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID);

        assertThat(userBookRating).isPresent();

        userBookRatingRepository.deleteByUserIdAndBookId(userBookRating.get().getUserBookRatingId().getUserId(), userBookRating.get().getUserBookRatingId().getBookId());

        assertThat(testEntityManager.find(UserBookRating.class, USER_BOOK_RATING_ID)).isNull();
        //TODO
        // org.springframework.dao.InvalidDataAccessApiUsageException: argument type mismatch
        //    assertThat( userBookRatingRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID)).isEmpty();
    }
}
