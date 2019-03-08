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