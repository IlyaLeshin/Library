package ru.otus.project.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.converters.UserFavoriteBookConverter;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookCreateDto;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookDto;
import ru.otus.project.exceptions.BookNotFoundException;
import ru.otus.project.exceptions.UserFavoriteBookNotFoundException;
import ru.otus.project.models.UserFavoriteBook;
import ru.otus.project.models.UserFavoriteBookId;
import ru.otus.project.repositories.BookRepository;
import ru.otus.project.repositories.UserFavoriteBookRepository;
import ru.otus.project.security.exceptions.UserNotFoundException;
import ru.otus.project.security.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserFavoriteBookServiceImpl implements UserFavoriteBookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final UserFavoriteBookRepository userFavoriteBookRepository;

    private final UserFavoriteBookConverter userFavoriteBookConverter;

    @Override
    @Transactional(readOnly = true)
    public List<UserFavoriteBookDto> findAllByUserId(long userId) {
        return userFavoriteBookRepository.findAllByUserId(userId).stream().map(userFavoriteBookConverter::modelToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserFavoriteBookDto findByUserIdAndBookId(long userId, long bookId) {
        return userFavoriteBookRepository.findByUserIdAndBookId(userId, bookId).map(userFavoriteBookConverter::modelToDto).orElseThrow(() ->
                new UserFavoriteBookNotFoundException("UserFavoriteBook with userId %s and bookId %s not found".formatted(userId, bookId)));
    }

    @Override
    @Transactional
    public UserFavoriteBookDto insert(UserFavoriteBookCreateDto userFavoriteBookCreateDto) {
        long userId = userFavoriteBookCreateDto.getUserId();
        long bookId = userFavoriteBookCreateDto.getBookId();

        return save(userId, bookId);
    }

    @Override
    @Transactional
    public void deleteByUserIdAndBookId(long userId, long bookId) {
        userFavoriteBookRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(long userId) {
        userFavoriteBookRepository.deleteAllByUserId(userId);
    }

    private UserFavoriteBookDto save(long userId, long bookId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(bookId)));

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(bookId)));

        var userFavoriteBook = userFavoriteBookRepository.save(new UserFavoriteBook(new UserFavoriteBookId(userId, bookId), user, book));

        return userFavoriteBookConverter.modelToDto(userFavoriteBook);
    }
}
