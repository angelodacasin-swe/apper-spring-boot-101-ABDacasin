import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.list;


/* Springboot Notes - Day 1 - ABDacasin

 https://docs.google.com/document/d/1ha59WV-W9nHAyq5fMnhdDj0tfZWi8m1ZQqsEBh-4ySU/

*/

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    // Create new book
    public String createBook(String title) {
        Book book = new Book();
        book.setId(idGenerator.nextId());
        book.setTitle(title);

        books.and(book);
    }
}

// READINGS -- MAKE SURE TO PUT NOTES!!
@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}

@Service
public class MyService {
    // Service implementation
}

@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}