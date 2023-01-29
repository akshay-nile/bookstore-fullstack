drop database if exists bookstore;
create database if not exists bookstore;
use bookstore;

create table if not exists books(
	id int primary key auto_increment,
	name text,
	author text,
	category text,
	price float
);

INSERT INTO books(name, author, category, price) VALUES('Harry Potter', 'J.K. Rowling', 'FICTION', 300.50);
INSERT INTO books(name, author, category, price) VALUES('Interstellar', 'Christopher Nolan', 'FANTACY', 780);
INSERT INTO books(name, author, category, price) VALUES('Jokes on Fire', 'Johny Liver', 'COMEDY', 450.20);
INSERT INTO books(name, author, category, price) VALUES('Wings of Fire', 'APJ Abdul Kalam', 'INSPIRATIONAL', 500.90);
INSERT INTO books(name, author, category, price) VALUES('Deep Dive', 'Josh Stolberg', 'MYSTERY', 120.70);
INSERT INTO books(name, author, category, price) VALUES('Thunderstorm', 'Mary Thompson', 'HORROR', 670.20);
INSERT INTO books(name, author, category, price) VALUES('The Man Who Knew Infinity', 'Tim Berner', 'INSPIRATIONAL', 900.30);
INSERT INTO books(name, author, category, price) VALUES('Sherlock Holmes', 'Arthur Doyle', 'MYSTERY', 420.70);
INSERT INTO books(name, author, category, price) VALUES('Hunger Games', 'Edgar Poe', 'FANTACY', 400.50);
INSERT INTO books(name, author, category, price) VALUES('Fearless', 'Jean Leckie', 'HORROR', 310.30);
INSERT INTO books(name, author, category, price) VALUES('The Edge of Laughter', 'Charly Dikken', 'COMEDY', 100.60);
INSERT INTO books(name, author, category, price) VALUES('Swindel', 'Agatha Cristine', 'FANTACY', 600);
INSERT INTO books(name, author, category, price) VALUES('Don\'t Turn Arround', 'Oscar Windley', 'HORROR', 540);
INSERT INTO books(name, author, category, price) VALUES('The Missing Child', 'Luice Hawk', 'MYSTERY', 230.40);
INSERT INTO books(name, author, category, price) VALUES('Holocost', 'Canon Bruice', 'INSPIRATIONAL', 880.20);
INSERT INTO books(name, author, category, price) VALUES('Dunes', 'Hellen King', 'FICTION', 930.60);
