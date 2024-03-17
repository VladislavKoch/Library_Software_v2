package ru.vladkochur.spring.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladkochur.spring.models.Book;
import ru.vladkochur.spring.models.Person;
import ru.vladkochur.spring.repositories.PeopleRepository;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Optional<Person> findOne(String name) {
        return peopleRepository.findOptionalByName(name);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getRelatedBooks(int ownerId) {
        Optional<Person> person = peopleRepository.findById(ownerId);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            Calendar c = Calendar.getInstance();                                //      Нахождение
            c.setTime(new Date());                                              //  даты 10 дней назад
            c.add(Calendar.DATE, -10);                                  //

            person.get().getBooks().forEach(book -> book.setOverdued(book.getTimeOfTaking().before(c.getTime())));
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }
}

