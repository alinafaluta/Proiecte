#include "Persoana.h"
#include <stdlib.h>
#include <string.h>

/*
Creeaza o persoana
Input - int id, char nume, prenume si int scor
Output - o variabila de tip Persoana
*/
Persoana creeaza(int id, char nume[20], char prenume[20], int scor)
{
	Persoana p;
	p.id = id;
	strcpy(p.nume, nume);
	strcpy(p.prenume, prenume);
	p.scor = scor;
	return p;
}

/*
Returneaza numele unui participant
Input - o variabila de tip Persoana
Output - char nume
*/
int getId(Persoana p)
{
	return p.id;
}
void getNume(Persoana p, char nume[20])
{
	strcpy(nume, p.nume);
}

/*
Returneaza prenumele unui participant
Input - o variabila de tip Persoana
Output - char prenume
*/

void getPrenume(Persoana p, char prenume[20])
{
	strcpy(prenume, p.prenume);
}

/*
Returneaza scorul unui participant
Input - o variabila de tip Persoana
Output - int scor
*/

int getScor(Persoana p)
{
	return p.scor;
}

void setNume(Persoana* p, char nume[20])
{
	strcpy(p->nume, nume);
}

void setPrenume(Persoana* p, char prenume[20])
{

	strcpy(p->prenume, prenume);
}

void setScor(Persoana* p, int scor)
{
	p->scor = scor;
}

bool equalPersoana(Persoana p1, Persoana p2)
{
	if (strcmp(p1.nume, p2.nume)) return false;
	if (strcmp(p1.prenume, p2.prenume)) return false;
	return true;
}