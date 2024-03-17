package ru.vladkochur.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladkochur.spring.models.Book;
import ru.vladkochur.spring.models.Person;
import ru.vladkochur.spring.repositories.BooksRepository;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public List<Book> findAll(Integer page, Integer booksPerPage, boolean isSorted) {
        boolean isPaginated = booksPerPage != null && (int) booksPerPage > 0 && (int) page >= 0;

        if (isPaginated && isSorted) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfCreation"))).getContent();

        } else if (isPaginated) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();

        } else if (isSorted) {
            return booksRepository.findAll(Sort.by("yearOfCreation"));

        } else {
            return booksRepository.findAll();
        }
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTimeOfTaking(null);
        });
    }

    @Transactional
    public void accept(int id, Person owner) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(owner);
            book.setTimeOfTaking(new Date());
        });
    }

    public List<Book> search(String searchQuery) {
        if (searchQuery.isEmpty()) {
            return null;
        }
        return booksRepository.findAllByTitleIgnoreCaseStartingWith(searchQuery).orElse(null);
    }
}
