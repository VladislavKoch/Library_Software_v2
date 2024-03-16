package ru.vladkochur.spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Это обязательное поле")
    @Size(min = 3, max = 100, message = "Имя должно быть минимум из 3 и максисум из 100 символов")
    private String name;

    @Column(name = "yearOfBirth")
    @Min(value = 1901, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }
}
