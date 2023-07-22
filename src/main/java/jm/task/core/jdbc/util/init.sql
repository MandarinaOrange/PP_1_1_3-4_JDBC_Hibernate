CREATE SCHEMA if NOT EXISTS test;
use test;

DROP TABLE if EXISTS users;
CREATE TABLE users(id int primary key auto_increment, name varchar(40), surname varchar(40), age smallint);

insert into users (name, surname, age) values ('Marina', 'Sheina', 26);

