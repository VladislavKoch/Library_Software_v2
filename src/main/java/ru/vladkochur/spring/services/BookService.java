package ru.vladkochur.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladkochur.spring.models.Book;
import ru.vladkochur.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> index() {
        return bookRepository.findAll();
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
        updatedBook.setOwnerId(id);
        bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
    @Transactional
    public void release(int id) {
        Optional<Book> optional=  bookRepository.findById(id);
        if (optional.isPresent()){
            Book book = optional.get();
            book.setOwnerId(null);
            bookRepository.save(book);
        }
    }
    @Transactional
    public void accept(int id, int owner_id) {
        Optional<Book> optional=  bookRepository.findById(id);
        if (optional.isPresent()){
            Book book = optional.get();
            book.setOwnerId(owner_id);
            bookRepository.save(book);
        }
    }
}