package ru.otus.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.project.models.*;
import ru.otus.project.security.models.User;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с избранными книгами пользовательей")
@DataJpaTest
class JpaUserFavoriteBookRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_USER_FAVORITE_BOOKS = 2;
    private static final long FIRST_USER_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final UserFavoriteBookId USER_FAVORITE_BOOK_ID = new UserFavoriteBookId(FIRST_USER_ID, FIRST_BOOK_ID);


    @Autowired
    private UserFavoriteBookRepository userFavoriteBookRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName("должен загружать книгу из избранного пользователем по userId и bookId")
    @Test
    void shouldReturnCorrectUserFavoriteBookById() {
        var optionalActualUserFavoriteBook = userFavoriteBookRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID);
        var expectedUserFavoriteBook = testEntityManager.find(UserFavoriteBook.class, USER_FAVORITE_BOOK_ID);

        assertThat(optionalActualUserFavoriteBook).isPresent().get()
                .isEqualTo(expectedUserFavoriteBook);
    }

    @DisplayName("должен загружать список избранных книг пользователя")
    @Test
    void shouldReturnCorrectUserFavoriteBookListByUserId() {
        var actualUserFavoriteBooks = userFavoriteBookRepository.findAllByUserId(FIRST_USER_ID);

        assertThat(actualUserFavoriteBooks).isNotNull().hasSize(EXPECTED_NUMBER_OF_USER_FAVORITE_BOOKS)
                .allMatch(book -> book.getUserFavoriteBookId() != null)
                .allMatch(book -> book.getUser() != null)
                .allMatch(book -> book.getBook() != null);
    }

    @DisplayName("должен сохранять книгу в избранные книги пользователя")
    @Test
    void shouldSaveUpdatedUserFavoriteBook() {
        var newUserFavoriteBook = new UserFavoriteBook();
        newUserFavoriteBook.setUserFavoriteBookId(USER_FAVORITE_BOOK_ID);
        var testUser = testEntityManager.find(User.class, FIRST_USER_ID);
        newUserFavoriteBook.setUser(testUser);
        var testBook = testEntityManager.find(Book.class, FIRST_BOOK_ID);
        newUserFavoriteBook.setBook(testBook);

        var expectedUserFavoriteBook = userFavoriteBookRepository.save(newUserFavoriteBook);

        assertThat(expectedUserFavoriteBook).isNotNull();

        var actualUserFavoriteBook = testEntityManager.find(UserFavoriteBook.class, expectedUserFavoriteBook.getUserFavoriteBookId());

        assertThat(actualUserFavoriteBook)
                .isNotNull()
                .isEqualTo(expectedUserFavoriteBook);

        assertThat(actualUserFavoriteBook)
                .matches(book -> book.getUserFavoriteBookId().getUserId().equals(newUserFavoriteBook.getUserFavoriteBookId().getUserId()))
                .matches(book -> book.getUserFavoriteBookId().getBookId().equals(newUserFavoriteBook.getUserFavoriteBookId().getBookId()))
                .matches(book -> book.getUser() != null)
                .matches(book -> book.getBook() != null);
    }

    @DisplayName("должен удалять книгу из избранного пользователя по id ")
    @Test
    void shouldDeleteUserFavoriteBook() {
        var userFavoriteBook = userFavoriteBookRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID);

        assertThat(userFavoriteBook).isPresent();

        userFavoriteBookRepository.deleteByUserIdAndBookId(userFavoriteBook.get().getUserFavoriteBookId().getUserId(), userFavoriteBook.get().getUserFavoriteBookId().getBookId());

        assertThat(userFavoriteBookRepository.findByUserIdAndBookId(FIRST_USER_ID, FIRST_BOOK_ID)).isEmpty();
    }

    @DisplayName("должен удалять все книги из избранного пользователя по userId ")
    @Test
    void shouldDeleteAllUserFavoriteBookByUserId() {
        assertThat(userFavoriteBookRepository.findAllByUserId(FIRST_USER_ID)).isNotEmpty();

        userFavoriteBookRepository.deleteAllByUserId(FIRST_USER_ID);

        assertThat(userFavoriteBookRepository.findAllByUserId(FIRST_USER_ID)).isEmpty();
    }
}
