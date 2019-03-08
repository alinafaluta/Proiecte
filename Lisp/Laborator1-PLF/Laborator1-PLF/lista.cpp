#include "lista.h"
#include <iostream>

using namespace std;


PNod creare_rec() {
	TElem x;
	cout << "x=";
	cin >> x;
	if (x == 0)
		return NULL;
	else {
		PNod p = new Nod();
		p->e = x;
		p->urm = creare_rec();
		return p;
	}
}

Lista creare() {
	Lista l;
	l._prim = creare_rec();
	return l;
}

void tipar_rec(PNod p) {
	if (p != NULL) {
		cout << p->e << " ";
		tipar_rec(p->urm);
	}
}

void tipar(Lista l) {
	tipar_rec(l._prim);
}

bool eListaVida(Lista l)
{
	if (l._prim == NULL)
		return true;
	else 
		return false;
}

Lista creeazaListaVida()
{
	Lista l;
	l._prim = NULL;
	return l;
}

int primElem(Lista l)
{
	return l._prim->e;
}

Lista sublista(Lista l)
{
	l._prim = l._prim->urm;
	return l;
}

Lista adaugaInceput(int elem, Lista l)
{
	PNod nou = new Nod();
	nou->e = elem;
	nou->urm = l._prim;
	l._prim = nou;
	return l;
}

bool eMultime(Lista l)
{
	if (eListaVida(l) == true)
		return true;
	return apare(primElem(l), sublista(l))== false && eMultime(sublista(l));
}

bool apare(int e, Lista l)
{
	if (eListaVida(l) == true)
		return false;
	if (e == primElem(l))
		return true;
	return apare(e, sublista(l));
}

int distincte(Lista l)
{
	if (eListaVida(l) == true)
		return 0;
	if (apare(primElem(l), sublista(l)) == false)
		return 1 + distincte(sublista(l));
	return distincte(sublista(l));
}

void verifMultime(Lista l)
{
	if (eMultime(l) == true)
		cout << "\n Lista este multime.\n";
	else
		cout << "\n Lista nu este multime.\n";
}

void verifDistincte(Lista l)
{
	cout << "Lista are " << distincte(l) << " elemente distincte. \n";
}

void distrug_rec(PNod p) {
	if (p != NULL) {
		distrug_rec(p->urm);
		delete p;
	}
}

void distrug(Lista l) {
	//se elibereaza memoria alocata nodurilor listei
	distrug_rec(l._prim);
}
