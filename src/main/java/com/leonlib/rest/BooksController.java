package com.leonlib.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leonlib.model.Book;
import com.leonlib.model.BookDetail;
import com.leonlib.repository.BookLikeRepository;
import com.leonlib.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BooksController {

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLikeRepository bookLikeRepository;
    
    @GetMapping("/booksCount")
    public long booksCount() {
        return bookRepository.count();
    }

    @GetMapping("/likes_count")
    public Map<String, Integer> likesCountByUser(@RequestParam("book_id") final Integer bookId) {
        final int count = bookLikeRepository.countByBookId(bookId);
        return Map.of("count", count);
    }

    @GetMapping("/check_like/{bookId}")
    public Map<String, String> checkLike(@PathVariable final Integer bookId, final HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session == null) {
            return Map.of("status", "unauthenticated");
        }

        final String sub = (String) session.getAttribute("sub");
        if (sub == null) {
            return Map.of("status", "unauthenticated");
        }

        final boolean hasBeenLiked = this.bookLikeRepository.likedBy(bookId, sub);

        return Map.of("status", hasBeenLiked ? "liked" : "not-liked");
    }

    @PostMapping("/like")
    public void likeBook(@RequestParam("book_id") final Integer bookId, final HttpServletRequest request) {
        final HttpSession session = request.getSession();

        if (session == null) {
            logger.info("user is unauthenticated");
            return;
        }

        final String sub = (String) session.getAttribute("sub");
        if (sub == null) {
            logger.info("user is unauthenticated 2)");
            return;
        }

        logger.info(String.format("like book %d", bookId));
        this.bookLikeRepository.likeByUser(bookId, sub);
    }

    @DeleteMapping("/like")
    public void unlikeBook(@RequestBody final Map<String, String> requestBody, final HttpServletRequest request) {
        final Integer bookId = Integer.valueOf(requestBody.get("book_id"));
        final HttpSession session = request.getSession();

        if (session == null) {
            return;
        }

        final String sub = (String) session.getAttribute("sub");
        if (sub == null) {
            return;
        }

        this.bookLikeRepository.unlikeByUser(bookId, sub);
    }

    @GetMapping("/books")
    public List<BookDetail> books(@RequestParam("author") final String author) {
        final List<BookDetail> results = new ArrayList<>();

        final List<Book> booksByAuthor = this.bookRepository.findByAuthorContaining(author);

        for (final Book book : booksByAuthor) {
            final BookDetail bookDetail = new BookDetail();
            bookDetail.setId(book.getId());
            bookDetail.setTitle(book.getTitle());
            bookDetail.setAuthor(book.getAuthor());
            bookDetail.setDescription(book.getDescription());
            bookDetail.setImageNames(book.getImageNames());

            results.add(bookDetail);
        }

        return results;
    }

}
