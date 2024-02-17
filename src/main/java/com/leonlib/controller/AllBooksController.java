package com.leonlib.controller;

import com.leonlib.model.Book;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonlib.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;


@Controller
public class AllBooksController {

    private static final Logger logger = LoggerFactory.getLogger(AllBooksController.class);

    private static final int numberOfResultsByPage = 20;

    @Autowired
    private BookRepository bookRepository;

    private void setUpPaginationFor(final int page, final long totalBooks, final Model model) {
        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("siteKey", "TODO: pending");
        
        final int totalPages = (int) Math.ceil(totalBooks / numberOfResultsByPage);
        
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("loggedIn", false);
        model.addAttribute("startPage", 1);
        model.addAttribute("endPage", totalPages);
    
        int start = 1;
        int end = totalPages;
    
        if (totalPages > 5) {
            if (page > 3) {
                start = page - 2;
                end = page + 2;
                if (end > totalPages) {
                    end = totalPages;
                    start = end - 4;
                }
            } else {
                end = 5;
            }
        }
    
        final List<Integer> pages = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            pages.add(i);
        }
    
        model.addAttribute("pages", pages);
        
        model.addAttribute("startPage", start);
        model.addAttribute("endPage", end);
    }
    
    @GetMapping("/allbooks")
    String about(@RequestParam(value = "page", required = false, defaultValue = "0") int page, final Model model) throws SQLException {
        final long bookCount = bookRepository.count();
        
        model.addAttribute("booksCount", bookCount);
        model.addAttribute("year", LocalDate.now().getYear());

        
        final PageRequest pageable = PageRequest.of(page, numberOfResultsByPage);
        final List<Book> results = bookRepository.findAllOrderedByTitle(pageable).getContent();

        model.addAttribute("results", results);

        this.setUpPaginationFor(page, bookCount, model);

        return "allbooks";
    }

}
