package ru.otus.project.listeners;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.book.rating.TotalBookRatingUpdateDto;
import ru.otus.project.dto.user.book.rating.UserBookRatingDto;
import ru.otus.project.services.TotalBookRatingService;

@Component
public class BookListener {

    private TotalBookRatingService totalBookRatingService;

    @PostPersist
    @PostUpdate
    @PostRemove
    public void postAnyChanges(UserBookRatingDto userBookRating) {
        TotalBookRatingUpdateDto totalBookRatingUpdateDto = new TotalBookRatingUpdateDto(userBookRating.getBookId());
        totalBookRatingService.update(totalBookRatingUpdateDto);
    }

}
