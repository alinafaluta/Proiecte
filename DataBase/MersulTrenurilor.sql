--cerinta 1
create database MersulTrenurilor;
go

use MersulTrenurilor;
go

create table Tipuri_Tren(
idTip int primary key identity,
Descriere varchar(100));
go

create table Trenuri(
idTren int primary key identity, 
Nume varchar(50),
idTip int foreign key references Tipuri_Tren(idTip) );
go

create table Rute(
idRuta int primary key identity,
Nume varchar(50),
idTren int foreign key references Trenuri(idTren));
go

create table Statii(
idStatie int primary key identity,
Nume varchar(50));
go


create table Traseu(
idStatie int foreign key references Statii(idStatie),
idRuta int foreign key references Rute(idRuta),
oraS time,
oraP time,
constraint pk_traseu primary key (idStatie,idRuta));
go
--versiunea 1 pentru cerinta 2
create procedure addTraseu (@ruta int, @statie int, @oraSos time, @oraPlec time)
as 
begin
	declare @idTraseu int
	begin try
		insert into Traseu(idStatie,idRuta,oraS,oraP)
		values (@statie,@ruta,@oraSos,@oraPlec)
	end try
	begin catch
		update Traseu set oraS=@oraSos, oraP = @oraPlec where idStatie=@statie and idRuta=@ruta
	end catch
end 
go

exec addTraseu 1,2, '11:20','11:45'
exec addTraseu 3,1, '11:20','11:45'
exec addTraseu 4,1, '11:20','11:45'
exec addTraseu 5,1, '11:20','11:45'
exec addTraseu 6,1, '11:20','11:45'
go

--versiunea 2 pt cerinta 2
create procedure addTraseu2 (@ruta int, @statie int, @oraSos time, @oraPlec time)
as 
begin
	declare @n int
	select @n=count(*) from Traseu where idRuta=@ruta and idStatie=@statie;
	if @n=0
		insert into Traseu(idStatie,idRuta,oraS,oraP)values (@statie,@ruta,@oraSos,@oraPlec)
		else
		update Traseu set oraS=@oraSos, oraP = @oraPlec where idStatie=@statie and idRuta=@ruta
end 
go

--cerinta 3
create view ViewRute
as
	select Rute.Nume
	from Traseu inner Join Rute on Traseu.idRuta=Rute.idRuta
	group by Rute.idRuta,Rute.Nume
	having count(*) = (select count(*) from Statii)
go

select * from ViewRute
go
--cerinta 4
create function NrMinTrenuri() returns table as return 
	select distinct Statii.idStatie,Statii.Nume from Statii inner join 
	Traseu on Traseu.idStatie=Statii.idStatie inner join
	Traseu TRS on Traseu.idStatie=TRS.idStatie and Traseu.idRuta<>TRS.idRuta
	where (Traseu.oraS>=TRS.oraS and Traseu.oraS<=TRS.oraP) or (Traseu.oraP>=TRS.oraS and Traseu.oraP<=TRS.oraP);
	go
	select * from NrMinTrenuri();
	go

	select*from Traseu

create table Teme 
( idTema INT PRIMARY KEY IDENTITY, descriere VARCHAR(100), deadline INT,
 CHECK (deadline > 0 and deadline <15),predare VARCHAR(50), CHECK (predare > 0 and predare <15))
 go

 create table Users ( username VARCHAR(200) PRIMARY KEY, password VARCHAR(1000), userType VARCHAR(20),
                        CONSTRAINT chk_userType CHECK (userType IN ('secretar', 'profesor', 'student')))
go
create table Students( idStudent INT PRIMARY KEY IDENTITY, nume VARCHAR(100), grupa INT, 
                       email VARCHAR(50), profesor VARCHAR(50) )
go

create table Inregistrari( id INT IDENTITY, idStudent INTEGER FOREIGN KEY REFERENCES Students(idStudent),idTema INTEGER FOREIGN KEY REFERENCES Teme(idTema),
                        nota FLOAT, feedback VARCHAR(1000),CONSTRAINT chk_inreg PRIMARY KEY (idStudent, idTema))