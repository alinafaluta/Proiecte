INSERT INTO Marci(Nume,Rating) VALUES ('Fares',1),('Lipton',2)

Select * from Marci

insert into Ceaiuri(Cid,Nume,Temperatura,Mid) Values(1,'verde',99,1)
insert into Ceaiuri VALUES(2,'negru',24,1)
select * from Ceaiuri

insert into Ingrediente VALUES ('apa','robinet',100),('tei','copac',50)
select * from Ingrediente

insert into Continuturi(Cid,Iid,CantitateC) VALUES (1,1,100),(1,2,25)
select * from Continuturi

SELECT M.Nume,C.Nume
FROM Marci M, Ceaiuri C
WHERE M.Nume = 'Fares' AND M.Mid = C.Mid

SELECT M.Nume,C.Nume
FROM Marci M INNER JOIN Ceaiuri C ON M.Mid = C.Mid

SELECT M.Nume,C.Nume
FROM Marci M LEFT OUTER JOIN Ceaiuri C ON M.Mid = C.Mid

SELECT M.Nume,C.Nume
FROM Marci M RIGHT OUTER JOIN Ceaiuri C ON M.Mid = C.Mid


SELECT M.Nume,C.Nume
FROM Marci M FULL JOIN Ceaiuri C ON M.Mid = C.Mid

SELECT C.Nume,I.Nume
FROM Ceaiuri C INNER JOIN Continuturi Co ON C.Cid = Co.Cid Inner Join Ingrediente I on Co.Cid=I.Iid
WHERE C.Nume LIKE 'n%' and Temperatura<=50

Select M.Mid,AVG(temperatura) as TEMpMedie
FROM Marci M inner join Ceaiuri C on M.Mid=C.Mid
Group by M.Mid

Select M.Mid,AVG(temperatura) as TEMpMedie
FROM Marci M inner join Ceaiuri C on M.Mid=C.Mid
Group by M.Mid 
Having AVG(Temperatura)>60

Select M.Mid,AVG(temperatura) as TEMpMedie
FROM Marci M inner join Ceaiuri C on M.Mid=C.Mid
Group by M.Mid 
Having M.Mid<=10