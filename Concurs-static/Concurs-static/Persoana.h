#pragma once
#define _CRT_SECURE_NO_WARNINGS
#pragma once
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <assert.h>

typedef struct {
	int id;
	char nume[20];
	char prenume[20];
	int scor;
}Persoana;

/*
Creeaza o persoana
Input - int id, char nume, prenume si int scor
Output - o variabila de tip Persoana
*/
Persoana creeaza(int id, char nume[20], char prenume[20], int scor);

/*
Returneaza numele unui participant
Input - o variabila de tip Persoana
Output - char nume
*/

void getNume(Persoana p, char nume[20]);

/*
Returneaza prenumele unui participant
Input - o variabila de tip Persoana
Output - char prenume
*/

void getPrenume(Persoana p, char prenume[20]);

/*
Returneaza scorul unui participant
Input - o variabila de tip Persoana
Output - int scor
*/

int getScor(Persoana p);
int getId(Persoana p);
void setNume(Persoana* p, char nume[20]);
void setPrenume(Persoana* p, char prenume[20]);
void setScor(Persoana* p, int scor);
bool equalPersoana(Persoana p1, Persoana p2);