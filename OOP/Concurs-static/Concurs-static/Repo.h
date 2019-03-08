#pragma once
#include "Persoana.h"

#define  PERSOANA_EXISTA_DEJA -2
#define PERSOANA_NU_EXISTA -1
#define SUCCES 0

typedef struct ListaPersoane{
	Persoana lista[1000];
	int nr_pers;
}ListaPersoane;

/*
Creeaza lista
*/
ListaPersoane makeList();

/*
Adauga persoana in repo
*/
int adauga(ListaPersoane* l, Persoana p);

/*
Sterge persoana
*/
int sterge(ListaPersoane * l, Persoana p);
int modificaPersoana(ListaPersoane *lista, Persoana p_vechi, Persoana p_nou);
/*output - lista cu id-ul persoanelor gasite
*/
int find(ListaPersoane* l, Persoana p);
/*
Returneaza persoana din ListaPersoanecu id-ul corespunzator
*/
Persoana getPersoana(ListaPersoane* l, int poz);

/*
Returneaza numarul de persoane din repo
*/
int size(ListaPersoane* l);

int setPersoana(ListaPersoane *lista, int poz, Persoana p_nou);