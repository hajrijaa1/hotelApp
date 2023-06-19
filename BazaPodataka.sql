create database ljeto

use ljeto


create table hotel 
(
	id int primary key auto_increment not null,
    ime varchar(30),
    mjesto varchar (30),
    cijena float,
    popustDjeca float
);

create table aranzman 
(
	id int primary key auto_increment not null,
    osoba varchar(25),
    brOdraslih int, 
    brDjece int,
    datumPrijave date,
    datumOdjave date,
    cijena float,
    idHotela int, 
    foreign key (idHotela) references hotel(id)
);

select * from hotel

select * from aranzman