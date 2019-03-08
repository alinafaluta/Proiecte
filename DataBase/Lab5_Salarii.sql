CREATE PROCEDURE CRUD_Salarii (@row int, @Job varchar(100), @flag int output )
AS
	BEGIN
	--Create=Insert
		declare @t varchar(100)
		declare @val int
		set @flag=1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Salarii;
		SELECT @val;
		declare @vali int
		exec @vali = dbo.ValidareRanduri @row
		if @vali>0
			while @row >0
			begin
				SET @t = @Job + CONVERT (VARCHAR(5), @val)
				insert into Salarii(Descriere, Suma) values (@t, 1500+@val)
				set @row=@row-1
				set @val=@val+1
			end
		else 
			set @flag = 0
		--Read=Select
		Select* from Salarii where Descriere like '%'+@Job+'%'
		--Update
		update Salarii set Suma = Suma+1000 where Suma<1600
		--Delete
		delete from Salarii where Suma>3000
		print 'Operati CRUD pentru tabelul Salarii'
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
EXEC CRUD_Salarii 4, 'Mun',@flag OUTPUT
if @flag=1 print 'Totul e ok'
else print 'HOPAAA,eroare!'
go

create view ViewCrudSalarii
as
	Select Descriere, Suma
	from Salarii
go

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'index_SalariiV')
DROP INDEX index_SalariiV ON Salarii;
GO  

CREATE NONCLUSTERED INDEX index_SalariiV ON Salarii(Descriere, Suma);
GO

select * from ViewCrudSalarii

select* from Salarii