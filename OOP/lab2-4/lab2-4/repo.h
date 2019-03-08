#pragma once
#include "oferta.h"
#include "vector.h"
#define OFERTA_EXISTA_DEJA -2
#define OFERTA_NU_EXISTA -1
#define SUCCES 0

typedef struct ListaOferte {
	Vector* vect;
} ListaOferte;

ListaOferte* creeareLista();
void destroyElemsRepo(ListaOferte* lista);
void distrugeLista(ListaOferte* lista);
int getDimList(ListaOferte* lista);
Vector* getVect(ListaOferte* lista);
int adaugaOferta(ListaOferte* lista, Oferta* of);
Oferta* stergeOferta(ListaOferte *lista, Oferta* of);
Oferta* modificaOferta(ListaOferte *lista, Oferta* of_vechi, Oferta* of_nou);
Oferta* setOferta(ListaOferte *lista, int poz, Oferta* of_nou);
int cautaOferta(ListaOferte *lista, Oferta* of);
Oferta* getOferta(ListaOferte *lista, int poz);
void testRepo();