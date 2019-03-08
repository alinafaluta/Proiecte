
create database InfoBiblioteci
go

use infoBiblioteci
go

create table Librarii(
	idLibrarie int primary key identity,
	nume varchar(30),
	adresa varchar(30)
);

create table Domenii(
	idDomeniu int primary key identity,
	descriere varchar(30)
);

create table Autori(
	idAutor int primary key identity,
	nume varchar(30),
	prenume varchar(30)
);

create table Carti(
	idCarte int primary key identity,
	titlu varchar(30),
	idDomeniu int foreign key references Domenii(idDomeniu)
);

create table CartiLibrarii(
	idCarte int foreign key references Carti(idCarte),
	idLibrarie int foreign key references Librarii(idLibrarie),
	dataAchizitie date,
	constraint pk_cartiLibrarii primary key (idCarte, idLibrarie)
);

create table CartiAutori(
	idCarte int foreign key references Carti(idCarte),
	idAutor int foreign key references Autori(idAutor),
	constraint pk_cartiAutori primary key (idCarte, idAutor)
);

insert into Autori(nume, prenume)
values ('nicu', 'peste'),
		('ionut', 'floare'),
		('nelu', 'morcov')

insert into Domenii(descriere)
values ('stiinta'),
		('sf'),
		('politist');

insert into Carti(titlu, idDomeniu)
values ('titlu1', 1),
		('titlu2', 2),
		('titlu3', 1),
		('titlu4', 2),
		('titlu5', 3)

insert into Librarii(nume, adresa)
values ('lib1', 'adr1'),
	   ('lib2', 'adr2'),
	   ('lib3', 'adr3'),
	   ('lib4', 'adr4')

insert into CartiAutori(idAutor, idCarte)
values(1, 2),
	  (1, 1),
	  (2, 2),
	  (2, 1),
	  (3, 2),
	  (1, 3)

insert into CartiLibrarii(idCarte, idLibrarie, dataAchizitie)
values (1, 1, '10-10-2011'),
	   (2, 1, '01-10-2010'),
	   (3, 1, '10-01-2002'),
	   (1, 2, '03-14-2011'),
	   (3, 2, '10-25-2010'),
	   (2, 3, '12-01-2011'),
	   (4, 1, '10-10-2011'),
	   (6, 1, '10-10-2011'),
	   (7, 1, '10-10-2011'),
	   (5, 2, '10-10-2008'),
	   (6, 2, '10-10-2008'),
	   (7, 2, '10-10-2008'),
	   (4, 2, '10-10-2011'),
	   (4, 3, '10-10-2011'),
	   (5, 1, '03-14-2011')


select * from Autori
select * from Carti
select * from Domenii
select * from CartiAutori
select * from Librarii
select * from CartiLibrarii
go
--1
create or alter procedure usp_addAutorCarte(
	@nume varchar(30), @prenume varchar(30), @idCarte int
) as
begin
	declare @idAutor int = 0;

	if not exists(select * from Autori
				  where @nume = nume and @prenume = prenume)
	begin
		insert into Autori(nume, prenume) values (@nume, @prenume)
		print('Autorul: ' + @nume + ' ' + @prenume + ' a fost adaugat');

		select @idAutor = idAutor
			from Autori
			where @nume = nume and @prenume = prenume

		insert into CartiAutori(idAutor, idCarte) values (@idAutor, @idCarte)
		print('Carte: ' + str(@idCarte) + ' adaugata');
		return 0;
	end

	select @idAutor = idAutor
		from Autori
		where nume = @nume and @prenume = prenume
	if exists (select * from CartiAutori where idAutor = @idAutor and idCarte = @idCarte)
	begin
		print('Legatura exista deja');
		return -1;
	end

	insert into CartiAutori(idAutor, idCarte) values (@idAutor, @idCarte)
	print('Carte: ' + str(@idCarte) + ' adaugata');
	return 0;

end
go

exec usp_addAutorCarte 'ion', 'petru', 2
exec usp_addAutorCarte 'ion', 'petru', 1
go

--2

create or alter view view_nrCarti
as
	select l.nume, count(cl.idCarte) as nrCarti
	from Librarii l
		inner join CartiLibrarii cl on cl.idLibrarie = l.idLibrarie
	where year(cl.dataAchizitie) > 2010
	group by l.idLibrarie, l.nume
	having count(cl.idCarte) >= 5
go

select * from view_nrCarti v
	order by v.nume desc;
go

--3

create or alter function uf_AfisareLista(
 @nrAutori int
)returns @Table table(Librarie int,
					  Adresa varchar(30),
					  Titlul varchar(30),
					  NrAutori int)
as
begin
	insert into @Table(Librarie, Adresa, Titlul, NrAutori)
			(select l.idLibrarie,
					l.adresa,
					c.titlu,
					count(ca.idAutor) as nrAutori
			 from Carti c
				inner join CartiAutori ca on ca.idCarte = c.idCarte
				inner join CartiLibrarii cl on c.idCarte = cl.idCarte
				inner join Librarii l on l.idLibrarie = cl.idLibrarie
			group by c.idCarte, l.idLibrarie, l.adresa, c.titlu
			having count(ca.idAutor) = @nrAutori)
			order by c.titlu

	return;
end
go

select * from uf_AfisareLista(3);