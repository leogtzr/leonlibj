package com.leonlib.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moandjiezana.toml.Toml;

import com.leonlib.model.Book;
import com.leonlib.repository.BookImagesRepository;
import com.leonlib.repository.BookRepository;

@Configuration
public class DbInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Value("classpath:data.toml")
    private Resource dataToml;

    @Autowired
    private BookImagesRepository bookImagesRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    @SuppressWarnings("unchecked")
    void initializeData() {
        try {
            final Toml toml = new Toml().read(dataToml.getInputStream());
            final List<Map<String, Object>> books = toml.getList("book");
            
            for (final Map<String, Object> book : books) {
                final Book entity = new Book();
                final Long id = (Long) book.get("id");
                entity.setId(id);
                entity.setTitle((String) book.get("title"));
                entity.setAuthor((String) book.get("author"));
                entity.setDescription((String) book.get("description"));
                final boolean hasBeenRead = Boolean.valueOf(String.valueOf(book.get("hasBeenRead")));
                entity.setHasBeenRead(hasBeenRead);
                
                entity.setImageNames((List<String>) book.get("imageNames"));
                entity.setAddedOn(String.valueOf(book.get("addedOn")));
                
                bookRepository.save(entity);
            }
            logger.info("data.toml read and processed successfully.");
            final long count = bookRepository.count();
            logger.info(String.format("debug:x Count = %d", count));
        } catch (IOException e) {
            logger.error("Error reading data.toml: " + e.getMessage());
        }
    }
}
