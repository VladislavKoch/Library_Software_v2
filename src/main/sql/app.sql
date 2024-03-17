CREATE TABLE Person
(
    id          int generated by default as identity PRIMARY KEY ,
    name        varchar(100) not null ,
    year_of_birth int not null
);

CREATE TABLE Book
(
    id             int generated by default as identity PRIMARY KEY ,
    title          varchar(100) not null ,
    author         varchar(100) not null ,
    year_of_creation int not null ,
    owner_id       int REFERENCES Person(id),
    time_of_taking timestamp
);

insert into Book(title, author, year_of_creation, owner_id, time_of_taking)  values ('Tak', 'Ya',2000,1, now() - INTERVAL '20 DAYS');
insert into Book(title, author, year_of_creation, owner_id, time_of_taking)  values ('Ogo', 'Work',2024,1, now() - INTERVAL '9 DAYS');
insert into Book(title, author, year_of_creation, owner_id, time_of_taking)  values ('Ogoo', 'Workk',2024,1, now() - INTERVAL '5 DAYS');
