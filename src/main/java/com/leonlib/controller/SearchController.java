package com.leonlib.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonlib.config.AppConfig;
import com.leonlib.model.Book;
import com.leonlib.model.BookSearchType;
import com.leonlib.repository.BookRepository;
import com.leonlib.utils.ModelAttributesHelper;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppConfig appConfig;

    private List<String> uniqueSearchTypes(String[] searchTypes) {
        return Arrays.stream(searchTypes)
            .distinct()
            .collect(Collectors.toList());
    }
    
    @GetMapping("/search_books")
    public ModelAndView search(@RequestParam("textSearch") final String bookQuery, @RequestParam("searchType") final String searchTypesStr) {
        List<String> searchTypesParams = uniqueSearchTypes(searchTypesStr.split(","));

        if (searchTypesParams.isEmpty() || (searchTypesParams.size() == 1 && StringUtils.isBlank(searchTypesParams.get(0))) ) {
            searchTypesParams = new ArrayList<>();
            searchTypesParams.add("byTitle");
        }

        final List<Book> bookResults = new ArrayList<>();

        for (final String searchTypeParam : searchTypesParams) {
            final BookSearchType searchType = BookSearchType.parse(searchTypeParam);
            switch (searchType) {
                case ByTitle:
                final List<Book> booksByTitle = this.bookRepository.findByTitleContaining(bookQuery);
                booksByTitle.forEach(bookResults::add);

                break;
                
                case ByAuthor:
                final List<Book> booksByAuthor = this.bookRepository.findByAuthorContaining(bookQuery);
                booksByAuthor.forEach(bookResults::add);

                break;

                case Unknown:
                    logger.info("error: search unknown");

                    final ModelAndView errorView = new ModelAndView("error", HttpStatus.BAD_REQUEST);
                    errorView.addObject("errorMessage", "Search type unknown");
                    ModelAttributesHelper.setCommonViewAttributes(errorView, this.bookRepository.count());
                    
                    return errorView;
            }
        }

        final ModelAndView model = new ModelAndView("search_books");
        model.addObject("siteKey", appConfig.getCaptchaSiteKey());
        model.addObject("results", bookResults);

        ModelAttributesHelper.setCommonViewAttributes(model, this.bookRepository.count());

        return model;
    }

}
