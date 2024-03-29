package ru.vladkochur.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vladkochur.spring.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
//    List<Book> findAllByOwnerId( int id );
    Optional<List<Book>> findAllByTitleIgnoreCaseStartingWith(String searchQuery);

}
