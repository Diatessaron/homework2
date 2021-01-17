package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl service;

    private final Book expectedUlysses = new Book(1L, "Ulysses", new Author(1, "James Joyce"),
            new Genre(1, "Modernist novel"));

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testSaveBookMethodWithParameters() {
        service.saveBook("Discipline and Punish", "Michel Foucault",
                "Philosophy");

        final Book actualBook = service.getBookById(2L);
        assertThat(actualBook).isNotNull().matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getTitle().equals("Discipline and Punish"))
                .matches(s -> s.getAuthor().getName().equals("Michel Foucault"))
                .matches(s -> s.getAuthor().getId() == 2)
                .matches(s -> s.getGenre().getName().equals("Philosophy"))
                .matches(s -> s.getGenre().getId() == 2);
    }

    @Test
    void testSaveBookMethodWithOldAuthorAndGenre() {
        service.saveBook("A Portrait of the Artist as a Young Man",
                "James Joyce", "Modernist novel");

        final Book actualBook = service.getBookById(2L);
        assertThat(actualBook).isNotNull().matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getTitle().equals("A Portrait of the Artist as a Young Man"))
                .matches(s -> s.getAuthor().getName().equals("James Joyce"))
                .matches(s -> s.getAuthor().getId() == 1)
                .matches(s -> s.getGenre().getName().equals("Modernist novel"))
                .matches(s -> s.getGenre().getId() == 1);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCorrectBookById() {
        final Book actual = service.getBookById(1L);

        assertEquals(expectedUlysses, actual);
    }

    @Test
    void shouldReturnCorrectBookByTitle() {
        final Book actual = service.getBookByTitle(expectedUlysses.getTitle());

        assertEquals(expectedUlysses, actual);
    }

    @Test
    void shouldReturnCorrectBookByAuthor() {
        final Book actual = service.getBookByAuthor(expectedUlysses.getAuthor().getName());

        assertEquals(expectedUlysses, actual);
    }

    @Test
    void shouldReturnCorrectBookByGenre() {
        final Book actual = service.getBookByGenre(expectedUlysses.getGenre().getName());

        assertEquals(expectedUlysses, actual);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCorrectBookByComment() {
        final Book actual = service.getBookByComment("Published in 1922");

        assertEquals(expectedUlysses, actual);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCorrectListOfBooks() {
        final Author author = new Author(0, "Michel Foucault");
        final Genre genre = new Genre(0, "Philosophy");
        final Book book = new Book(0, "Discipline And Punish", author, genre);
        final List<Book> expected = List.of(expectedUlysses, book);

        service.saveBook("Discipline And Punish", "Michel Foucault", "Philosophy");
        final List<Book> actual = service.getAll();

        assertThat(actual).isNotNull().matches(a -> a.size() == expected.size());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testUpdateBookMethodWithParameters() {
        service.updateBook(1L, "Discipline and Punish", "Michel Foucault",
                "Philosophy");

        final Book actualBook = service.getBookById(1L);
        assertThat(actualBook).isNotNull().matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getTitle().equals("Discipline and Punish"))
                .matches(s -> s.getAuthor().getName().equals("Michel Foucault"))
                .matches(s -> s.getAuthor().getId() == 2)
                .matches(s -> s.getGenre().getName().equals("Philosophy"))
                .matches(s -> s.getGenre().getId() == 2);
    }

    @Test
    void testUpdateBookMethodWithOldAuthorAndGenre() {
        service.updateBook(1L, "A Portrait of the Artist as a Young Man",
                "James Joyce", "Modernist novel");

        final Book actualBook = service.getBookById(1L);
        assertThat(actualBook).isNotNull().matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getTitle().equals("A Portrait of the Artist as a Young Man"))
                .matches(s -> s.getAuthor().getName().equals("James Joyce"))
                .matches(s -> s.getAuthor().getId() == 1)
                .matches(s -> s.getGenre().getName().equals("Modernist novel"))
                .matches(s -> s.getGenre().getId() == 1);
    }
}
