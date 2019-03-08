--modifica tipul unei coloane;
CREATE PROCEDURE P1
AS 
	BEGIN
		ALTER TABLE Autovehicule
		ALTER COLUMN Tip varchar(60) NOT NULL
		PRINT 'P1:Am modificat tipul coloanei Tip din tabelul Autovehicule din varchar(30) in varchar(60) not null'
	END
GO
--sterge tipul unei coloane
CREATE PROCEDURE I1
AS 
	BEGIN
		ALTER TABLE Autovehicule
		ALTER COLUMN Tip varchar(30)
		PRINT 'I1:Am refacut tipul coloanei Tip din tabelul Autovehicule din varchar(60) not null in varchar(30)'
	END
GO
--adauga o costrângere de “valoare implicită” pentru un câmp;
CREATE PROCEDURE P2
AS 
	BEGIN
		ALTER TABLE Terenuri
		ADD CONSTRAINT DF_Terenuri DEFAULT 'ha' FOR UnitateDeMasura
		PRINT 'P2:Am aplicat constrangerea default DF_Terenuri asupra campului UnitateDeMasura din tabelul Terenuri, acum valoarea default fiind "ha" '
	END
GO
--sterge o costrângere de “valoare implicită” pentru un câmp;
CREATE PROCEDURE I2
AS 
	BEGIN
		ALTER TABLE Terenuri
		DROP CONSTRAINT DF_Terenuri 
		PRINT 'I2:Am sters constrangerea default DF_Terenuri ce era aplicata asupra campului UnitateDeMasura din tabela Terenuri'
	END
GO
--creea/o tabelă;
CREATE PROCEDURE P3
AS 
	BEGIN
		CREATE TABLE Sedinte(
			IdS INT PRIMARY KEY,
			DataSedintei date,
			Ora time,
			OrdineaDeZi varchar(255))
		PRINT 'P3:Am creat un nou tabel "Sedinte" cu urmatoarele campuri: IdS -cheia primara, DataSedintei, Ora,OrdineaDeZi'
	END
GO
--sterge o tabelă;
CREATE PROCEDURE I3
AS 
	BEGIN
		DROP table Sedinte
		PRINT 'I3:Am sters tabelul Sedinte'
	END
GO
--adăuga un câmp nou;
CREATE PROCEDURE P4
AS 
	BEGIN
		Alter table Sedinte
		Add Locatie Int
		PRINT 'P4:Am adaugat la tabelul Sedinte campul Locatie'
	END
GO
--sterge un câmp nou;
CREATE PROCEDURE I4
AS 
	BEGIN
		Alter table Sedinte
		Drop Column Locatie
		PRINT 'I4:Am sters campul Locatie din tabelul Sedinte'
	END
GO

--creearea unei constrângere de cheie străină
CREATE PROCEDURE P5
AS 
	BEGIN
		Alter table Sedinte
		Add Constraint fk_locatie FOREIGN KEY (Locatie) REFERENCES Adrese(Aid)
		PRINT 'P5:Am adaugat la tabelul Sedinte constrangerea fk_locatie care pune campul locatie drept cheie straina din tabelul Adrese (Aid)'
	END
GO
--stergerea unei constrângere de cheie străină
CREATE PROCEDURE I5
AS 
	BEGIN
		Alter table Sedinte
		DROP Constraint fk_locatie 
		PRINT 'I5:Am eliminat din tabelul Sedinte constrangerea fk_locatie care pune campul locatie drept cheie straina din tabelul Adrese (Aid)'
	END
GO

exec P3
exec P4
exec P5
exec I5
exec I4
exec I3
go

--creare tabel versiune si punerea valorii 0
create table Versiune(
	Vers int primary key)

insert into Versiune (Vers) values (0)
GO
-- procedura care executa modificarile in functie de o valoare data
CREATE PROCEDURE Modif
		@v INT
AS
	BEGIN
	declare @ver_init int
	if @v<0 or @v>5
	begin
		print 'Parametrul dat nu este corect. Putem converti la versiuni intre 0-5'
		return 0
	end
	select top 1 @ver_init=Vers FROM Versiune
	while @ver_init < @v
		begin
			if @ver_init = 0
				exec P1
			if @ver_init = 1
				exec P2
			if @ver_init = 2
				exec P3
			if @ver_init = 3
				exec P4
			if @ver_init = 4
				exec P5
			set @ver_init = @ver_init + 1
		end
	while @ver_init > @v
		begin
			if @ver_init = 1
				exec I1
			if @ver_init = 2
				exec I2
			if @ver_init = 3
				exec I3
			if @ver_init = 4
				exec I4
			if @ver_init = 5
				exec I5
			set @ver_init = @ver_init - 1
		end
	UPDATE Versiune set Vers = @ver_init
	END
GO

exec Modif 3
exec Modif 0
exec Modif 5
Select * From Versiune
exec Modif 3
Select * From Versiune

exec Modif -8


go
CREATE PROCEDURE InserareP (@row int)
AS 
	BEGIN	
		declare @t varchar(30)
		declare @val int
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Proiecte
		SELECT @val
		while @row >0
			begin
			if @row%2=0
				begin
					SET @t = 'Proiect' + CONVERT (VARCHAR(5), @val)
					insert into Proiecte(Descriere, Aprobare) values (@t, 'DA')
					set @row=@row-1
					set @val=@val+1
				end
			else 
				begin
					SET @t = 'Proiect' + CONVERT (VARCHAR(5), @val)
					insert into Proiecte(Descriere, Aprobare) values (@t, 'NU')
					set @row=@row-1
					set @val=@val+1
				end
			end
	END
GO

exec InserareP 50
select * from Proiecte
go

CREATE PROCEDURE InserareC (@row int)
AS 
	BEGIN	
		declare @t varchar(13)
		declare @val int
		declare @c varchar(5)
		set @val = 1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Cetateni
		SELECT @t = CNP
			FROM Cetateni
			WHERE Cid =1
		while @row >0
			begin
				begin try
					set @c =convert(varchar(5),@val)
					insert into Cetateni (CNP, DataNasterii, LoculNasterii, Aid, Nume, InitialaTatalui,Prenume) 
					values (@t, '1989-09-15', 'Falticeni'+ @c, 2, 'Nume'+@c, 'A','Prenume'+@c)
					set @row=@row-1
				end try
				begin catch
				end catch
					set @val=@val+1
					set @t= convert(bigint,@t)+1
					set @t = convert(varchar(13),@t)
			end
	END
GO

exec InserareC 40
go
select * from Cetateni

