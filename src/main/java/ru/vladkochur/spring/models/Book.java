package ru.vladkochur.spring.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {
    private int id;
    @NotEmpty(message = "Это обязательное поле")
    @Size(min = 1, max = 100, message = "Название должно быть минимум из 1 и максисум из 100 символов")
    private String title;
    @NotEmpty(message = "Это обязательное поле")
    @Size(min = 3, max = 100, message = "Имя должно быть минимум из 3 и максисум из 100 символов")
    private String author;
    @Min(value = 1600, message = "Год создания должен быть корректным")
    @Max(value = 2050, message = "Год создания должен быть корректным")
    private int yearOfCreation;
    private Integer owner_id;

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

    public Integer getOwner_id() {
        if(owner_id != null){
        return owner_id;} else {
            return 0;
        }
    }

    public void setOwner_id(Integer ownerID) {
        this.owner_id = ownerID;
    }

    public Book() {
    }
}
