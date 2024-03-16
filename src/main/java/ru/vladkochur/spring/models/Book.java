package ru.vladkochur.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Это обязательное поле")
    @Size(min = 1, max = 100, message = "Название должно быть минимум из 1 и максисум из 100 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Это обязательное поле")
    @Size(min = 3, max = 100, message = "Имя должно быть минимум из 3 и максисум из 100 символов")
    private String author;
    @Column(name = "yearOfCreation")
    @Min(value = 1600, message = "Год создания должен быть корректным")
    @Max(value = 2050, message = "Год создания должен быть корректным")
    private int yearOfCreation;

    @Column(name = "owner_id")
    private Integer ownerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public Integer getOwnerId() {
//        if(owner_id != null){
//        return owner_id;} else {
//            return 0;
//        }
        return ownerId;
    }

    public void setOwnerId(Integer ownerID) {
        this.ownerId = ownerID;
    }

    public Book() {
    }
}
