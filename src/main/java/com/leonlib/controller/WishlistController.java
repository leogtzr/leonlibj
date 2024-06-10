package com.leonlib.controller;

import com.leonlib.model.Book;
import com.leonlib.model.WishListBook;
import com.leonlib.repository.WishlistRepository;
import com.leonlib.utils.ModelAttributesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WishlistController {

    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);

    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping("/wishlist")
    String about(final HttpServletRequest request, final Model model) throws SQLException {
        final HttpSession session = request.getSession();

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("booksCount", wishlistRepository.count());

        final List<WishListBook> results = wishlistRepository.findAllOrderedByTitle();
        model.addAttribute("results", results);

        model.addAttribute("numberOfBooks", ((wishlistRepository.count() / 100) + 1) * 100);

        ModelAttributesHelper.setLoggedInAttributesInModel(model, session);

        return "wishlist";
    }

}
