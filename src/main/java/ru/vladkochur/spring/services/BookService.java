package ru.vladkochur.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladkochur.spring.models.Book;
import ru.vladkochur.spring.models.Person;
import ru.vladkochur.spring.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> index(Integer page, Integer booksPerPage, boolean isSorted) {
        boolean isPaginated = booksPerPage != null && (int) booksPerPage > 0 && (int) page >= 0;

        if (isPaginated && isSorted) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfCreation"))).getContent();

        } else if (isPaginated) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();

        } else if (isSorted) {
            return bookRepository.findAll(Sort.by("yearOfCreation"));

        } else {
            return bookRepository.findAll();
        }
    }

    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getRelatedBooks(int ownerId) {
        return bookRepository.findAllByOwnerId(ownerId);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void accept(int id, Person owner) {
        bookRepository.findById(id).ifPresent(book -> book.setOwner(owner));
    }

    public List<Book> search(String searchQuery) {
        if (searchQuery.isEmpty()){
            return null;
        }
            return bookRepository.findAllByTitleStartingWith(searchQuery).orElse(null);
    }
}
