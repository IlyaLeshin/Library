package ru.otus.project.controllers.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class UserPageController {

    @GetMapping("/users/{id}")
    public String userPage(@PathVariable long id, Model model) {
        model.addAttribute("userId", id);
        return "users/user";
    }

    @GetMapping("/users/{userId}/ratings/books/{bookId}/editing-form")
    public String editingFormUserRatingBookPage(@PathVariable long userId, @PathVariable long bookId, Model model) {
       model.addAttribute("userId", userId);
       model.addAttribute("bookId", bookId);
        return "users/ratings/books/editing-form";
    }

}