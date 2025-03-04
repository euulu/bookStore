package org.eulu.bookshop;

import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BookShopApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Test book");
            book.setAuthor("Test author");
            book.setIsbn("qwer");
            book.setPrice(BigDecimal.valueOf(25));
            book.setDescription("This is the description of a test book.");
            book.setCoverImage("https://url.com");
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
