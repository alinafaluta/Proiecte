#include "Persoana.h"
#include <stdlib.h>
#include <string.h>
#include "Repo.h"

/*
Creeaza lista
*/
ListaPersoane makeList()
{
	ListaPersoane list;
	list.nr_pers = 0;
	return list;
}

/*
Adauga persoana in ListaPersoane
*/
int adauga(ListaPersoane * l, Persoana p)
{
	int poz = find(l, p);
	if (poz != -1) return PERSOANA_EXISTA_DEJA;
	p.id = l->nr_pers+1;
	l->lista[l->nr_pers] = p;
	l->nr_pers++;
	return SUCCES;
}

/*
Sterge persoana
*/
int sterge(ListaPersoane * l, Persoana p)
{
	int poz = find(l, p);
	if (poz == -1) 
		return PERSOANA_NU_EXISTA;
	p.id = poz;
	for (int i = poz; i <l->nr_pers - 1; ++i) {
		l->lista[i] = l->lista [i + 1];
	}
	l->nr_pers--;
	return SUCCES;
}


int modificaPersoana(ListaPersoane *lista, Persoana p_vechi, Persoana p_nou)
{
	int poz = find(lista, p_vechi);
	if (poz == -1) return PERSOANA_NU_EXISTA;
	lista->lista[poz] = p_nou;
	return SUCCES;
}

int setPersoana(ListaPersoane *lista, int poz, Persoana p_nou)
{
	if (poz < 0 || poz > size(lista)) return PERSOANA_NU_EXISTA;
	lista->lista[poz] = p_nou;
	return SUCCES;
}
/*
Gaseste persoana dupa nume, prenume,scor
output - id-ul persoaneigasite
*/
int find(ListaPersoane* l, Persoana p)
{
	for (int i = 0; i < l->nr_pers; ++i) {
		if (equalPersoana(p, getPersoana(l, i))) return i;
	}
	return PERSOANA_NU_EXISTA;
}

/*
Returneaza persoana din ListaPersoane de pe pozitia data
*/
Persoana getPersoana(ListaPersoane* l, int poz)
{
	return l->lista[poz];
}

/*
Returneaza numarul de persoane din ListaPersoane
*/
int size(ListaPersoane * l)
{
	return l->nr_pers;
}