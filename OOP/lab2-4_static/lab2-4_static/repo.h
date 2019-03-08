#pragma once
#include "oferta.h"
#define  OFERTA_EXISTA_DEJA -2
#define OFERTA_NU_EXISTA -1
#define SUCCES 0

typedef struct ListaOferte {
	Oferta oferte[100];
	int dim;
} ListaOferte;

ListaOferte creeareLista();
int getDim(ListaOferte *lista);
int adaugaOferta(ListaOferte *lista, Oferta of);
int stergeOferta(ListaOferte *lista, Oferta of);
int modificaOferta(ListaOferte *lista, Oferta of_vechi, Oferta of_nou);
int setOferta(ListaOferte *lista, int poz, Oferta of_nou);
int cautaOferta(ListaOferte *lista, Oferta of);
Oferta getOferta(ListaOferte *lista, int poz);
void testRepo();