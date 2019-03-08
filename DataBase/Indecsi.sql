create table Ceaiuri2(Cid int primary key identity, Denumire varchar(30), Pret int)
insert into Ceaiuri2 Values('Ceai de menta', 10), ('Tei',8),('Fructe',8),('ghimbir',12)

Select * from Ceaiuri2 order by Denumire

if exists(select name from sys.indexes
where name = 'N_idx_Ceaiuri2_Denumire')
drop index N_idx_Ceaiuri2_Denumire on Ceaiuri2

create nonclustered index N_idx_Ceaiuri2_Denumire
on Ceaiuri2(Denumire)


select * from sys.indexes
where name like 'N%'

select * from Ceaiuri2
order by Denumire
