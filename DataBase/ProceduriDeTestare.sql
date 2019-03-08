--insereaza un nr k de elemente in tabela Fonduri;
CREATE PROCEDURE InserareF (@row int)
AS 
	BEGIN	
		declare @t varchar(30)
		declare @val int
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Fonduri
		SELECT @val
		while @row >0
			begin
				SET @t = 'Fondul' + CONVERT (VARCHAR(5), @row)
				insert into Fonduri(Descriere, Suma) values (@t, @row*100)
				set @row=@row-1
				set @val=@val+1
			end
	END
GO
--sterge primele k inregistrari din tabela Fonduri
CREATE PROCEDURE StergereF (@row int)
AS 
	BEGIN
		declare @val int
		set @val = 1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM Fonduri
		if(@row>@val)
		begin
			set @row=@val
			end
		while @row>0
		begin
			delete top (1) from Fonduri
			set @row = @row-1
		end
	END
GO
--insereaza un nr k de elemente in tabela AjutoareSociale;
CREATE PROCEDURE InserareAS (@row int)
AS 
	BEGIN	
		declare @t varchar(13)
		declare @val int
		set @val = 1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM AjutoareSociale
		while @row >0
			begin
				begin try
					SELECT @t = CNP
					FROM Cetateni
					WHERE Cid = @val
					insert into AjutoareSociale(CNP, Suma) values (@t, 300+@val)
					set @row=@row-1
				end try
				begin catch
				end catch
					set @val=@val+1
					set @t = convert(varchar(13),@t)
			end
	END
GO
--sterge primele k elemente din tabela AjutoareSociale
CREATE PROCEDURE StergeAS (@row int)
AS
	BEGIN
		declare @val int
		set @val = 1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM AjutoareSociale
		if(@row>@val)
		begin
			set @row=@val
			end
		while @row>0
		begin
			delete top (1) from AjutoareSociale
			set @row = @row-1
		end
	END
go
--insereaza un nr k de elemente in tabela FoP;
CREATE PROCEDURE InserareFoP (@row int)
AS 
	BEGIN	
		declare @t varchar(30)
		declare @valF int
		declare @valP int
		declare @start int
		SET NOCOUNT ON;
		SELECT @valF =COUNT(*) 
        FROM Fonduri
		SELECT @valF
		SELECT @valP =COUNT(*) 
        FROM Proiecte
		SELECT @valP
		set @start=@valP
		while @row >0 and @valF>0
			begin
			begin try
				insert into FoP(Fid,Pid,CNP) values (@valF,@valP,'2980509336372')
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
	END
GO

--sterge primele k inregistrari din tabela FoP
CREATE PROCEDURE StergeFoP (@row int)
AS
	BEGIN
		declare @val int
		set @val = 1
		SET NOCOUNT ON;
		SELECT @val =COUNT(*) 
        FROM FoP
			set @row=@val
		while @row>0
		begin
			delete top (1) from FoP
			set @row = @row-1
		end
	END
go
exec InserareAS 10
select * from AjutoareSociale
go

create view ViewFonduri
as
	Select * 
	from Fonduri
go

create view ViewAS
as
	Select c.CNP,c.Nume,c.InitialaTatalui,c.Prenume,a.Suma
	from AjutoareSociale a inner join Cetateni C on a.CNP=c.CNP
go

create view ViewFoP
as
	Select count(F.Fid) Proiecte, Aprobare
	From Fonduri F 
	inner join FoP Fp on Fp.Fid = F.Fid 
	inner join Proiecte P on  P.Pid = Fp.Pid
	Group by P.Aprobare 
go

declare @t datetime
declare @s datetime
set @t=getdate()
print @t
Select count(F.Fid) Proiecte, Aprobare
	From Fonduri F 
	inner join FoP Fp on Fp.Fid = F.Fid 
	inner join Proiecte P on  P.Pid = Fp.Pid
	Group by P.Aprobare 
set @s=getdate()
print @s
go

create procedure TestFoP
AS
BEGIN
	declare @t datetime
	declare @s datetime
	declare @d datetime
	declare @tableId int
	declare @testId int
	declare @procedure1 varchar(50)
	declare @procedure2 varchar(50)
	declare @rows int
	declare @TestRunId int
	declare @ViewId int
	select @tableId=TableID
	from Tables
	where Name='FoP'

	select @testId=TestId
	from TestTables
	where TableID = @tableId

	select @rows=NoOfRows
	from TestTables
	where TableID = @tableId and TestID=@testId

	select @procedure1 = Name
	From Tests
	Where TestID=@testId
	print @procedure1
	select @procedure2 = Name
	From Tests
	Where TestID=@testId-3
	print @procedure2
	
	exec InserareF @rows
	--print @t
	set @t=getdate()
	print @t
	exec @procedure2 @rows
	exec @procedure1 @rows
	set @s=getdate()
	print @s
	select* from ViewFoP
	set @d= getdate()
	print @d
	insert into TestRuns(Description,StartAt,EndAt) values ('TestFoP',@t ,@d)

	select @TestRunId = TestRunID
	from TestRuns 
	where Description = 'TestFoP'and StartAt=@t and EndAt=@d

	insert into TestRunTables values(@TestRunId,@tableId,@t,@s)

	select @ViewId=ViewID
	from Views
	Where Name='ViewFoP'
	--print @ViewId
	insert into TestRunViews values(@TestRunId,@ViewId,@s,@d)
END
go

exec StergereF 200
exec TestFonduri
select * from Fonduri

create procedure TestFonduri
AS
BEGIN
	declare @t datetime
	declare @s datetime
	declare @d datetime
	declare @tableId int
	declare @testId int
	declare @procedure1 varchar(50)
	declare @procedure2 varchar(50)
	declare @rows int
	declare @TestRunId int
	declare @ViewId int
	select @tableId=TableID
	from Tables
	where Name='Fonduri'

	select @testId=TestId
	from TestTables
	where TableID = @tableId

	select @rows=NoOfRows
	from TestTables
	where TableID = @tableId and TestID=@testId

	select @procedure1 = Name
	From Tests
	Where TestID=@testId
	print @procedure1
	select @procedure2 = Name
	From Tests
	Where TestID=@testId-3
	print @procedure2
	
	--exec InserareF @rows
	--print @t
	set @t=getdate()
	print @t
	exec @procedure2 @rows
	exec StergeFoP @rows
	exec @procedure1 @rows
	set @s=getdate()
	print @s
	select* from ViewFoP
	set @d= getdate()
	print @d
	insert into TestRuns(Description,StartAt,EndAt) values ('TestFonduri',@t ,@d)

	select @TestRunId = TestRunID
	from TestRuns 
	where Description = 'TestFonduri'and StartAt=@t and EndAt=@d

	insert into TestRunTables values(@TestRunId,@tableId,@t,@s)

	select @ViewId=ViewID
	from Views
	Where Name='ViewFonduri'
	--print @ViewId
	insert into TestRunViews values(@TestRunId,@ViewId,@s,@d)
END
go


create procedure TestAjutoareSociale
AS
BEGIN
	declare @t datetime
	declare @s datetime
	declare @d datetime
	declare @tableId int
	declare @testId int
	declare @procedure1 varchar(50)
	declare @procedure2 varchar(50)
	declare @rows int
	declare @TestRunId int
	declare @ViewId int
	select @tableId=TableID
	from Tables
	where Name='AjutoareSociale'

	select @testId=TestId
	from TestTables
	where TableID = @tableId

	select @rows=NoOfRows
	from TestTables
	where TableID = @tableId and TestID=@testId

	select @procedure1 = Name
	From Tests
	Where TestID=@testId
	print @procedure1
	select @procedure2 = Name
	From Tests
	Where TestID=@testId-3
	print @procedure2
	
	--exec InserareF @rows
	--print @t
	set @t=getdate()
	print @t
	exec @procedure2 @rows
	exec @procedure1 @rows
	set @s=getdate()
	print @s
	select* from ViewAS
	set @d= getdate()
	print @d
	insert into TestRuns(Description,StartAt,EndAt) values ('TestAjutoareSociale',@t ,@d)

	select @TestRunId = TestRunID
	from TestRuns 
	where Description = 'TestAjutoareSociale'and StartAt=@t and EndAt=@d

	insert into TestRunTables values(@TestRunId,@tableId,@t,@s)

	select @ViewId=ViewID
	from Views
	Where Name='ViewAS'
	--print @ViewId
	insert into TestRunViews values(@TestRunId,@ViewId,@s,@d)
END
go
exec TestFoP
exec TestFonduri
exec TestAjutoareSociale

select * from AjutoareSociale
exec InserareAS 20
