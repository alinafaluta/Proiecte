CREATE PROCEDURE CRUD_FoP (@row int, @CNP varchar(13), @flag int output )
AS
	BEGIN
	--Create=Insert
		declare @t varchar(100)
		declare @valF int
		declare @valP int
		declare @start int
		SET NOCOUNT ON;
		SELECT @valF =COUNT(*) 
        FROM Fonduri
		--SELECT @valF
		SELECT @valP =COUNT(*) 
        FROM Proiecte
		--SELECT @valP
		set @start=@valP
		set @flag=1
		declare @vali int
		declare @val int
		exec @vali = dbo.ValidareRanduri @row
		--exec @val = dbo.ValidareCNP @CNP
		if @vali>0 
		while @row >0 and @valF>0
			begin
			begin try
				insert into FoP(Fid,Pid,CNP) values (@valF,@valP,@CNP)
				set @row=@row-1
			end try
			begin catch
			end catch
			if @valP>1
					begin
						set @valP=@valP-1
					end
				else
					begin
						set @valP=@start
						set @valF=@valF-1
					end
			end
		else 
			set @flag = 0
		--Read=Select
		Select* from FoP
		--Update
		update  FoP set CNP='2980509336372'
		--Delete
		delete from FoP where CNP like '2980509336373'
		print 'Operati CRUD pentru tabelul FoP'
	END
GO

create function ValidareCNP ( @a varchar(13))
returns int
as 
begin 
	declare @val int
	if @a ='2980509336372'
		set @val = 1
	else
		set @val = 0
	return @val
	end
	go

	
Declare @flag bit
EXEC CRUD_FoP 4, 'Mun',@flag OUTPUT
if @flag=1 print 'Totul e ok'
else print 'HOPAAA,eroare!'
go
select*From FoP
go
create view ViewCrudF
as
	Select *
	from FoP
go

select* from ViewCrudF