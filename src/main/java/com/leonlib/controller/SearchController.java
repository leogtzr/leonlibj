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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonlib.model.Book;
import com.leonlib.model.BookSearchType;
import com.leonlib.repository.BookRepository;
import com.leonlib.utils.ModelAttributesHelper;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /*
     * func uniqueSearchTypes(searchTypes []string) []string {
	set := make(map[string]struct{})
	var result []string

	for _, item := range searchTypes {
		if _, exists := set[item]; !exists {
			set[item] = struct{}{}
			result = append(result, item)
		}
	}

	return result
}
     */

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
                logger.info(String.format("debug:x (by title) found=(%s)", booksByTitle));

                booksByTitle.forEach(bookResults::add);
                //  pending
                break;
                
                case ByAuthor:
                final List<Book> booksByAuthor = this.bookRepository.findByAuthorContaining(bookQuery);
                logger.info(String.format("debug:x (by author) found=(%s)", booksByAuthor));

                booksByAuthor.forEach(bookResults::add);
                break;

                case Unknown:
                    logger.info("error: search unknown");

                    // TODO: pending redirection... redirectToErrorPageWithMessageAndStatusCode(w, "Wrong search", http.StatusInternalServerError)

            }
        }

        final ModelAndView model = new ModelAndView("search_books");
        ModelAttributesHelper.setCommonViewAttributes(model, this.bookRepository.count());
        model.addObject("results", bookResults);

        return model;
    }

}
