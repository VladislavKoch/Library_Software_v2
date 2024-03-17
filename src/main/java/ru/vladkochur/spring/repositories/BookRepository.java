package ru.vladkochur.spring.repositories;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vladkochur.spring.models.Book;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByOwnerId( int id );

    Optional<List<Book>> findAllByTitleStartingWith(String searchQuery);

}
