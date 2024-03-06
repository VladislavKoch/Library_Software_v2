package ru.vladkochur.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vladkochur.spring.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("Select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("Select * from Book where id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into Book (title, author, yearofcreation) values (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYearOfCreation());
    }
    public List<Book> getRelatedBooks(int personId){
        return jdbcTemplate.query("Select * from book where owner_ID = ?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update book SET title=? , author=?, yearofcreation=? where id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfCreation(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public void release(int id){
        jdbcTemplate.update("update book SET owner_ID=null where id=?", id);
    }
    public void accept(int id, int owner_id){
        jdbcTemplate.update("update book SET owner_ID=? where id=?", owner_id, id);
    }
}
