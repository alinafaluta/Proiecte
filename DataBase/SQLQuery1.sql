Create Database Ceainarie
Go
Use  Ceainarie
GO

Create Table Marci(
	Mid INT PRIMARY KEY IDENTITY,
	Nume VARCHAR(50),
	Rating INT NOT NULL)

Create Table Ingrediente(
	Iid INT Primary Key IDENTITY,
	Nume VARCHAR(50), --DEFAULT 'Batman',
	Origine VARCHAR(20),
	Cantitate INT DEFAULT 1)

Create Table Ceaiuri(
	Cid INT PRIMARY KEY,
	Nume VARCHAR(20),
	Temperatura INT CHECK(Temperatura>=0 AND Temperatura <= 100),
	Mid INT FOREIGN KEY REFERENCES Marci(Mid))

Create Table Continuturi(
	Cid INT FOREIGN KEY REFERENCES Ceaiuri(Cid),
	Iid INT FOREIGN KEY REFERENCES Ingrediente(Iid),
	CantitateC INT,
	CONSTRAINT pk_Continuturi PRIMARY KEY(Cid,Iid))

Create Table CoduriDeBare(
	Coid INT FOREIGN KEY REFERENCES Ceaiuri(Cid),
	NrLL INT,
	CONSTRAINT pk_Cod PRIMARY KEY(Coid))
