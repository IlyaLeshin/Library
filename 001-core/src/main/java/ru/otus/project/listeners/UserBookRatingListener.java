package ru.otus.project.listeners;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.models.UserBookRating;

@Component
@RequiredArgsConstructor
public class UserBookRatingListener {

    @PostPersist
    @PostUpdate
    @PostRemove
    public void postAnyPersistOrUpdateChanges(UserBookRating userBookRating) {
        var totalBookRating = userBookRating.getUserBookRatingId().getBookId();
//        todo
//        make changer averageRating in TotalBookRating after changed UserBookRating
    }

}
