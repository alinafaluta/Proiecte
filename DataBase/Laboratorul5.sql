CREATE PROCEDURE CRUD_Cetateni (@row int, @LoculNasterii varchar(100),@Aid int, @flag int output )
AS
	BEGIN
	--Create=Insert
		declare @t varchar(13)
		declare @val int
		declare @c varchar(5)
		set @val = 1
		set @flag =1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Cetateni
		SELECT @t = CNP
			FROM Cetateni
			WHERE Cid =60;
		declare @vali int
		exec @vali = dbo.ValidareAdresa @Aid
		if @vali = 1 
			while @row >0
			begin
				begin try
					set @c =convert(varchar(5),@val)
					insert into Cetateni (CNP, DataNasterii, LoculNasterii, Aid, Nume, InitialaTatalui,Prenume) 
					values (@t, '1989-09-15', @LoculNasterii, @Aid, 'Nume'+@c, 'A','Prenume'+@c)
					IF @@TRANCOUNT > 0 SET @flag=1;
					set @row=@row-1
				end try
				begin catch
				end catch
					set @val=@val+1
					--declare @x varchar(13)
					exec @t = dbo.PrelucrareCNP @t
					--set @t= convert(bigint,@t)+1;
	set @t = convert(varchar(13),@t);
			end
		else 
			set @flag = 0
		--Read=Select
		Select* from Cetateni where LoculNasterii = @LoculNasterii
		--Update
		update Cetateni set InitialaTatalui='GH' where LoculNasterii = 'Iasi'
		--Delete
		delete from Cetateni where Aid>10 and Aid<12
		print 'Operati CRUD pentru tabelul Cetateni'
	END
GO

select * from Cetateni where LoculNasterii='Cluj'
go

Declare @flag bit
EXEC CRUD_Cetateni 2,'Fufu',6, @flag OUTPUT
if @flag=1 print 'Totul e ok'
else print 'HOPAAA,eroare!'
go

create function PrelucrareCNP (@t varchar(13))
returns varchar(13)
as
begin
	set @t= convert(bigint,@t)+1;
	set @t = convert(varchar(13),@t);
	return @t;
end
go

create function ValidareAdresa ( @a int)
returns int
as 
begin 
	declare @val int
	if @a > 0
		set @val = 1
	else
		set @val = 0
	return @val
	end
	go


create view ViewCrudCtateni
as
	Select LoculNasterii,Aid,Nume,InitialaTatalui,Prenume
	from Cetateni where InitialaTatalui = 'GH'
go

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'index_CetateniV')
DROP INDEX index_CetateniV ON Cetateni;
GO  

CREATE NONCLUSTERED INDEX index_CetateniV ON Cetateni(LoculNasterii,Aid,Nume,InitialaTatalui,Prenume);
GO

select* from ViewCrudCtateni

CREATE PROCEDURE CRUD_Fonduri (@row int, @Descriere varchar(100), @flag int output )
AS
	BEGIN
	--Create=Insert
		declare @t varchar(100)
		declare @val int
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Fonduri;
		SELECT @val;
		declare @vali int
		exec @vali = dbo.ValidareRanduri @row
		if @vali>0
			while @row >0
			begin
				SET @t = @Descriere + CONVERT (VARCHAR(5), @val)
				insert into Fonduri(Descriere, Suma) values (@t, @row*100)
				if @@TRANCOUNT > 0 set @flag =1
				set @row=@row-1
				set @val=@val+1
				set @t = convert(varchar(13),@t);
			end
		else 
			set @flag = 0
		--Read=Select
		Select* from Fonduri where Descriere like '%'+@Descriere+'%'
		--Update
		update Fonduri set Suma = Suma +1 where Suma>1000
		--Delete
		delete from Fonduri where Suma >30900
		print 'Operati CRUD pentru tabelul Fonduri'
	END
GO

create function ValidareRanduri ( @a int)
returns int
as 
begin 
	declare @val int
	if @a > 0
		set @val = 1
	else
		set @val = 0
	return @val
	end
	go

	
Declare @flag bit
EXEC CRUD_Fonduri 4, 'Ce',@flag OUTPUT
if @flag=1 print 'Totul e ok'
else print 'HOPAAA,eroare!'
go

create view ViewCrFonduri
as
	Select Descriere, Suma
	from Fonduri where Suma>1000
go

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'index_FonduriV')
DROP INDEX index_FonduriV ON Fonduri;
GO  

CREATE NONCLUSTERED INDEX index_FonduriV ON Fonduri(Descriere,Suma);
GO

select * from ViewCrudFonduri