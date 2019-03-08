create database Vanzari
go
go

use Vanzari
 --1--- crearea celor 4 tabele
 create table Clienti (
 idClient int primary key identity,
 denumire varchar(50),
 codFiscal int)

 create table Produse(
 idProd int primary key identity,
 denumire varchar(50),
 unitateDeMasura varchar(20))

 create table AgentiDeVanzare(
 idAgent int primary key identity,
 nume varchar(50),
 prenume varchar(50))

 create table Facturi(
 numar int identity primary key,
 dataEmiterii datetime,
 idClient int foreign key references Clienti(idClient),
 idAgent int foreign key references AgentiDeVanzare(idAgent))

 create table Vanzari(
 nrOrdine int identity primary key,
 pret int,
 cantitate int,
 numar int foreign key references Facturi(numar),
 idProd int foreign key references Produse(idProd))
 go


 --populam tabelele
 insert into Clienti(denumire,codFiscal) 
values
('SC Forever SRL','12345'),
('SC Amway SRL','23456')

insert into AgentiDeVanzare(nume,prenume)
values
('Popa','Vlad'),
('Nistor','Maria') 

insert into Produse(denumire,unitateDeMasura)
values
('Crema hidratanta','ml'),
('Blush','g')

insert into Facturi(dataEmiterii,idClient,idAgent)
values
('2019-01-02',1,1),
('2018-12-03',1,2),
('2018-11-20',1,2),
('2018-11-21',2,1),
('2018-09-19',2,2)

insert into Vanzari (pret,cantitate,numar,idProd)
values
(55,2,1,1),
(40,3,2,2),
(20,5,3,1),
(60,10,4,2),
(25,3,5,2)
go


 ---procedura de inserare
 create or alter procedure InserareVanzare(@numar int, @idProd int, @nrOrdie int, @pret int ,@cantitate int)
 as 
 begin 
	begin try
		insert into Vanzari(nrOrdine,pret,cantitate,numar,idProd)
		values (@nrOrdie,@pret,@cantitate,@numar,@idProd)
	end try
	begin catch
		update Vanzari set cantitate=cantitate-2*cantitate where nrOrdine=@nrOrdie and idProd=@idProd and numar=@numar
	end catch
 end 
 go


 exec InserareVanzare 1,1,1,31,5

select * from Vanzari
go

--crearea view-ului
create view ViewFacturi
as
	select Clienti.denumire, Facturi.numar,Facturi.dataEmiterii,Vanzari.cantitate*Vanzari.pret as 'valoareTotala'
	from Facturi 
	inner Join Clienti on Facturi.idClient=Clienti.idClient 
	inner join Vanzari on Vanzari.numar=Facturi.numar
	inner join Produse on Vanzari.idProd = Produse.idProd
	where Produse.denumire='Blush' and Vanzari.cantitate*Vanzari.pret>=300
go

select * from ViewFacturi
go
--functia

create or alter function Tabelas()
returns @Lista table (Luna int, NumeAgent varchar(30), PrenumeAgent varchar(30), ValoareTotala int)
as  
begin
	insert into @Lista 
		select Month(F.dataEmiterii) as 'Luna', A.nume as 'NumeAgent', A.prenume as 'PrenumeAgent', 
			sum(V.cantitate*V.pret) as 'ValoareTotala'
			from Facturi F
			inner join AgentiDeVanzare A on A.idAgent=F.idAgent
			inner join Vanzari V on V.numar=F.numar
			group by Month(F.dataEmiterii), A.nume,A.prenume
return;
end;
go

select * from Tabelas()
go