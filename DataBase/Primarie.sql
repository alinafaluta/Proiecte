create table FoP(
	Fid int foreign key references Fonduri(Fid),
	Pid int foreign key references Proiecte(Pid),
	Descriere varchar(255),
	CNP varchar(13) foreign key references Cetateni(CNP) default '2980509336372',
	constraint pk_fp primary key (Fid,Pid))

insert into Adrese (Tara,Judet,Oras,Cartier,Comuna,Sat,Strada,Numarul,Bloc,Scara,Etaj,Apartament) values
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'Frenci',2,'4F',3,4,15),
('Romania','Suceava','Suceava','George Enescu',NULL,NULL,'Fulgului',3,'4',2,4,16),
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'Alecu Russo',23,'2A',1,5,20),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Maior Ioan',2,'5B',1,6,24),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Al. I. Cuza',2,'6',2,1,3),
('Romania','Suceava','Vatra Dornei','Dornele',NULL,NULL,'Fuji',2,'4',2,2,7),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Aleea Lalelelor',2,'4F',3,3,12),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Aleea Lalelelor',2,'5C',4,4,13),
('Romania','Suceava','Radauti','Burdujeni',NULL,NULL,'Gogoasei',2,'1D',5,5,22),
('Romania','Suceava','Vicovul de sus','Burdujeni',NULL,NULL,'Bicom',2,'3',3,4,12),
('Romania','Suceava','Vatra Dornei',NULL,NULL,NULL,'Dornelor',2,'5C',2,7,26),
('Romania','Suceava',NULL,NULL,'Slatina','Slatina','Caisului',1,'3B',1,3,9)

insert into Adrese (Tara,Judet,Oras,Cartier,Comuna,Sat,Strada,Numarul,Bloc,Scara,Etaj,Apartament) values
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'Frenci',2,'4F',3,4,13),
('Romania','Suceava','Suceava','George Enescu',NULL,NULL,'Fulgului',3,'4',2,4,15),
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'Alecu Russo',23,'2A',1,5,21),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Maior Ioan',2,'5B',1,6,22),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Al. I. Cuza',2,'6',2,1,2),
('Romania','Suceava','Vatra Dornei','Dornele',NULL,NULL,'Fuji',2,'4',2,2,6),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Aleea Lalelelor',2,'4F',3,3,11),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Aleea Lalelelor',2,'5C',4,4,12),
('Romania','Suceava','Radauti','Burdujeni',NULL,NULL,'Gogoasei',2,'1D',5,5,21),
('Romania','Suceava','Vicovul de sus','Burdujeni',NULL,NULL,'Bicom',2,'3',3,4,13),
('Romania','Suceava','Vatra Dornei',NULL,NULL,NULL,'Dornelor',2,'5C',2,7,25),
('Romania','Suceava',NULL,NULL,'Slatina','Slatina','Caisului',1,'3B',1,3,8)

insert into Adrese (Tara,Judet,Oras,Cartier,Comuna,Sat,Strada,Numarul,Bloc,Scara,Etaj,Apartament) values
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'Bujorului',2,'4F',3,4,13),
('Romania','Suceava','Suceava','George Enescu',NULL,NULL,'Aleea Saturn',3,'4',2,4,15),
('Romania','Suceava','Suceava','Burdujeni',NULL,NULL,'George Cosbuc',23,'2A',1,5,21),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Marului',2,'5B',1,6,22),
('Romania','Suceava','Falticeni',NULL,NULL,NULL,'Ficusului',2,'6',2,1,2),
('Romania','Suceava','Vatra Dornei','Dornele',NULL,NULL,'Apelor',2,'4',2,2,6),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Sincai',2,'4F',3,3,11),
('Romania','Suceava','Gura Humorului',NULL,NULL,NULL,'Sincai',2,'5C',4,4,12),
('Romania','Suceava','Radauti','Burdujeni',NULL,NULL,'Vico',2,'1D',5,5,21),
('Romania','Suceava','Vicovul de sus','Burdujeni',NULL,NULL,'Industriei',2,'3',3,4,13),
('Romania','Suceava','Vatra Dornei',NULL,NULL,NULL,'Dunelor',2,'5C',2,7,25),
('Romania','Suceava',NULL,NULL,'Slatina','Slatina','Cimitirului',1,'3B',1,3,8)

insert into Cetateni (CNP, DataNasterii, LoculNasterii, Aid, Nume, InitialaTatalui,Prenume) values
('2890915224722', '1989-09-15', 'Falticeni', 2, 'Filipoiu', 'D','Ioana'),
('2890915224724', '1989-09-15', 'Falticeni', 4, 'Faluta', 'V','Alina'),
('2890915224723', '1989-09-15', 'Falticeni', 22, 'Fardea', 'Gh','Maria'),
('1911102450600', '1991-11-02', 'Suceava', 3, 'Marchizul', 'D','Ion'),
('1921102450634', '1992-11-02', 'Falticeni', 4, 'Faluta', 'V','Alin'),
('1921102450612', '1992-11-02', 'Suceava', 37, 'Irim', 'F','Ionut'),
('1921102450611', '1992-11-02', 'Suceava', 6, 'Popa', 'I','Marian'),
('1860310100640', '1986-03-10', 'Vatra-Dornei', 33, 'Vasiliu', 'T','Miron'),
('1760310100640', '1976-03-10', 'Gura Humorului', 23, 'Pop', 'F','Mihai'),
('1930310100640', '1993-03-10', 'Suceava', 18, 'Gheorgiu', 'Gh','Vasile'),
('1940310100640', '1994-03-10', 'Radauti', 19, 'Popescu', 'V','Alexandru'),
('1920310100640', '1992-03-10', 'Vatra-Dornei', 11, 'Mitocaru', 'A','Andrei')

insert into Salarii (Descriere, Suma) values
('Ingrijtor spatii verzi', 1800),
('Fochist', 2200),
('Contabil', 2500),
('Sofer autocar', 1800),
('Distribuitor', 1600),
('Sofer autobuz scolar', 1800),
('Primar',  3000),
('Viceprimar', 2800),
('Inginer de sistem', 2400),
('Pompier voluntar', 1000),
('Distribuitor ajutoare sociale', 1800),
('Avocat', 2800),
('Politist', 2400),
('Agent economic', 2300)

insert into Fonduri(Descriere, Suma) values
('FunTak', 100),
('AGF', 1200),
('DRF', 1300),
('GHT', 900),
('GGG', 1800),
('UNIlot', 2800),
('EUR', 1400),
('AlpinOil', 1100),
('Titans', 1600),
('Funds', 2200),
('Oils', 70000),
('TERRAP', 20000),
('RRT', 3400),
('Jovial', 5000),
('Aure', 2300),
('MuraSan', 12300)

insert into Proiecte (Descriere, Aprobare) values
('Canalizare','DA'),
('Drumuri comunale','DA'),
('Poduri','NU'),
('Parcuri','DA'),
('Terenuri de sport','NU'),
('Skate Park','NU'),
('Parcari','NU'),
('Gradinite','DA'),
('Asfaltari','NU'),
('Iluminare publica','DA')

Alter table Contracte 
drop column Functia

Alter table FoP
drop column Descriere

Alter table Contracte
add CNP varchar(13) foreign key references Cetateni(CNP)


Alter table Cetateni
drop constraint CK__Cetateni__CNP__5070F446
go

Alter table Cetateni
add Cid int identity
--constraint pk_fp primary key (Cid,CNP)
go

drop table Angajati

insert into Cetateni (CNP, DataNasterii, LoculNasterii, Aid, Nume, InitialaTatalui,Prenume) values
('1890915224722', '1989-09-15', 'Falticeni', 5, 'Filipoiu', 'D','Ioan'),
('1890915224724', '1989-09-15', 'Falticeni', 7, 'Faluta', 'V','Alin'),
('1890915224723', '1989-09-15', 'Falticeni', 11, 'Fardea', 'Gh','Mariua'),
('2911102450600', '1991-11-02', 'Suceava', 12, 'Marchizul', 'D','Ioana'),
('2921102450634', '1992-11-02', 'Falticeni', 13, 'Faluta', 'V','Alina'),
('2921102450612', '1992-11-02', 'Suceava', 14, 'Irim', 'F','Ionela'),
('2921102450611', '1992-11-02', 'Suceava', 15, 'Popa', 'I','Mariana'),
('2860310100640', '1986-03-10', 'Vatra-Dornei', 16, 'Vasiliu', 'T','Mirela'),
('2760310100640', '1976-03-10', 'Gura Humorului', 17, 'Pop', 'F','Mihaela'),
('2930310100640', '1993-03-10', 'Suceava', 28, 'Gheorgiu', 'Gh','Vasilica'),
('2940310100640', '1994-03-10', 'Radauti', 29, 'Popescu', 'V','Alexandra'),
('2920310100640', '1992-03-10', 'Vatra-Dornei', 31, 'Mitocaru', 'A','Andreea')

