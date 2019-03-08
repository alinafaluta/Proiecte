create  database GestionareZboruri
go

use GestionareZboruri
go

--Scrieti un script care creeaza modelul realtiona
create table OrasePlecari(
idOP int primary key identity,
nume varchar(40),
captiala bit)

create table Companii(
idC int primary key identity,
denumire varchar(40),
anInfiintare date)

create table Avioane(
idA int primary key identity,
denumire varchar(40),
tip varchar(40),
greutate int,
viteza int,
nrDeLocuri int,
idC int foreign key references Companii(idC))

create table Pasageri(
idP int primary key identity,
nume varchar(40),
varsta int,
nationalitate varchar(40),
idOP int foreign key references OrasePlecari(idOP))

create table Zboruri(
idOP int foreign key references OrasePlecari(idOp),
idC int foreign key references Companii(idC),
dataPlecarii datetime2,
constraint pk_zboruri primary key (idOP,idC))
go

--populare tabele

insert into OrasePlecari(nume,captiala) values ('Roma',1),('Milano',0), ( 'Iasi',0),('Bucuresti',1)

insert into Companii(denumire,anInfiintare) values ('WizzAir','2010-01-01'),('Tarom','2012-03-04')

insert into Avioane (denumire,tip,greutate,viteza,nrDeLocuri,idC) values
('Avionas1','Boeing777', 1000,3400,500,1),
('Avionas2','Boeing777', 1000,3400,500,1),
('Avionas3','Boeing777', 1000,3400,500,1),
('Avionas1','Boeing', 1000,3400,500,2),
('Avionas1','Privat', 1000,3400,50,2)

insert into Pasageri(nume,varsta,nationalitate,idOP) values
('Elena',23,'italian',4)

insert into Zboruri(idOP,idC,dataPlecarii) values(1,1,' 2019-01-01 23:00'),(2,1,'2018-12-12 12:12'),(2,2, ' 2018-11-11 11:11')
select * from OrasePlecari
select * from Avioane
select * from Companii
select * from Pasageri
select * from Zboruri
go

--procedura stocata
create or alter procedure AdaugaZbor(@idOP int, @idC int, @data datetime2)
as 
begin
	declare @n int
	select @n=count(*) from Zboruri where idOP = @idOP and idC=@idC;
	if @n=0
		insert into Zboruri(idOP,idC,dataPlecarii)values (@idOP,@idC,@data)
		else
		update Zboruri set dataPlecarii=@data where idOP = @idOP and idC=@idC
end 
go

exec AdaugaZbor 3,1,' 2019-01-01 20:00'
select * from Zboruri
go

--functie care afiseaza pentru fiecare companie pasagerii majori
create or alter function AfisareMajori()
returns @Lista table (IdCompanie int,Denumire varchar(40),NumePasager varchar(40),Varsta int)
as 
begin
	insert into @Lista
			select Zboruri.idC as 'IdCompanie', Companii.denumire as 'Denumire',Pasageri.nume as 'NumePasager', Pasageri.varsta as 'Varsta'
			from Zboruri
			inner join Companii on Zboruri.idC=Companii.idC
			inner join OrasePlecari on Zboruri.idOP = OrasePlecari.idOP
			inner join Pasageri on OrasePlecari.idOP = Pasageri.idOP
			where Pasageri.varsta>=18
			group by Zboruri.idC,Companii.denumire,Pasageri.nume,Pasageri.varsta
			order by Zboruri.idC desc
return;
end;
go

select * from AfisareMajori()
go