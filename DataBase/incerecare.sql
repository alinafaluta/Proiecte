create database Biblioteca
go

use Biblioteca
go

--- 1
create table Autori(idA int primary key identity, nume varchar(40))

create table Domenii(idD int primary key identity, descriere varchar(20))

create table Librarii(idL int primary key identity, nume varchar(40),adresa varchar(40))

create table Carti(
idC int primary key identity,
titlu varchar(20),
idD int foreign key references Domenii(idD), 
idL int foreign key references Librarii(idL),
dataCumpararii datetime,
autor int foreign key references Autori(idA))
go

--populare tabele
insert into Autori(nume) values ('Dostowvski'), ('Creanga'),('Tolstoi')
insert into Domenii(descriere) values ('stiintific'),('clasic'),('literatura'),('beletristica')
insert into Librarii(nume,adresa) values('Alexandria','George Enescu, Aleea Lalelelor'),('Carturesti','Iullius Mall, Parter')
insert into Carti(titlu,idD,iDL,dataCumpararii,autor) values('carte1',1,1,'2019-01-02',1),('carte1',1,1,'2019-01-02',2),('carte2',4,2,'2018-01-03',3)
insert into Carti(titlu,idD,iDL,dataCumpararii,autor) values('carte7',1,1,'2009-01-02',1)
go
--procedura stocata
create or alter procedure Add_autori(@nume varchar(40),@idC int)
as 
begin
	declare @idA int 
	declare @idAu int
	declare @idD int
	declare @idL int 
	declare @data datetime
	declare @titlu varchar(20)
	select @idA = COUNT(*) from Autori where nume=@nume;
	select @titlu=titlu,@idD = idD,@idL = idL, @data = dataCumpararii from Carti where idC=@idC;
	if @idA = 0
		begin
			insert into Autori(nume) values(@nume)
			select @idAu=idA from Autori where nume = @nume
			insert into Carti(titlu,idD,iDL,dataCumpararii,autor) values(@titlu,@idD,@idL,@data,@idAu)
		end
	else 
		begin
			select @idAu=idA from Autori where nume = @nume
			insert into Carti(titlu,idD,iDL,dataCumpararii,autor) values(@titlu,@idD,@idL,@data,@idAu)
		end
end 
go

exec Add_autori 'Autor',1

select * from Carti
go

select * from Autori
go

--cerinta 3
create or alter view ShowCarti
as

	select l.nume, count(cl.idC) as nrCarti
	from Librarii l
		inner join Carti cl on cl.idL = l.idL
	where year(cl.dataCumpararii) > 2010
	group by l.idL, l.nume
	having count(cl.idC) >= 2
go


select*from Carti

select * from ShowCarti
go
--4
create or alter function Carticele(@nrAutori int)
returns @Lista table (Libraria varchar(40), Adresa varchar(40), Titlu varchar(20), NrAutori int)
as 
begin
		insert into @Lista
			select Librarii.nume as 'Libraria', Librarii.adresa as 'Adresa',c.titlu as 'Titlu', count(c.titlu) as 'NrAutori'
			from Carti c
			inner join
			Librarii on c.idL=Librarii.idL
			group by Librarii.nume,Librarii.adresa,c.titlu
			having count(c.titlu)=@nrAutori
return;
end;
go

select * from Carticele(1)
go