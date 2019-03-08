CREATE PROCEDURE CRUD_Proiecte (@row int, @Descriere varchar(100), @flag int output )
AS
	BEGIN
	--Create=Insert
		declare @t varchar(100)
		declare @val int
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Proiecte;
		SELECT @val;
		declare @vali int
		exec @vali = dbo.ValidareRanduri @row
		if @vali>0
			while @row >0
			begin
			if @row%2=0
				begin
					SET @t = @Descriere + CONVERT (VARCHAR(5), @val)
					insert into Proiecte(Descriere, Aprobare) values (@t, 'DA')
					if @@TRANCOUNT>0 set @flag =1
					set @row=@row-1
					set @val=@val+1
				end
			else 
				begin
					SET @t = @Descriere + CONVERT (VARCHAR(5), @val)
					insert into Proiecte(Descriere, Aprobare) values (@t, 'NU')
					set @row=@row-1
					set @val=@val+1
				end
			end
		else 
			set @flag = 0
		--Read=Select
		Select* from Proiecte where Descriere like '%'+@Descriere+'%'
		--Update
		update Proiecte set Aprobare='DA' where Pid>50
		--Delete
		delete from Proiecte where Aprobare='NU' and Pid>60
		print 'Operati CRUD pentru tabelul Proiecte'
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
EXEC CRUD_Proiecte 4, 'Ce',@flag OUTPUT
if @flag=1 print 'Totul e ok'
else print 'HOPAAA,eroare!'
go

create view ViewCrudProiecte
as
	Select Descriere, Aprobare
	from Proiecte
go

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'index_ProiecteV')
DROP INDEX index_ProiecteV ON Proiecte;
GO  

CREATE NONCLUSTERED INDEX index_ProiecteV ON Proiecte(Descriere, Aprobare);
GO

select * from ViewCrudProiecte