package ru.vladkochur.spring.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Column(name = "year_of_creation")
    @Min(value = 1600, message = "Год создания должен быть корректным")
    @Max(value = 2050, message = "Год создания должен быть корректным")
    private int yearOfCreation;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "time_of_taking")
    @Temporal( TemporalType.TIMESTAMP)
    private Date timeOfTaking;

    public boolean isOverdued() {
        return isOverdued;
    }

    public void setOverdued(boolean overdued) {
        isOverdued = overdued;
    }

    @Transient
    private boolean isOverdued;

    public Date getTimeOfTaking() {
        return timeOfTaking;
    }

    public void setTimeOfTaking(Date timeOfTaking) {
        this.timeOfTaking = timeOfTaking;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                ", owner=" + owner +
                '}';
    }

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    public Book() {
    }
}
