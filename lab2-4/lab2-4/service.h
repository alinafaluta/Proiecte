#pragma once
#include "repo.h"
#include "validator.h"
#define SORTAT 4
#define NESORTAT 5
#define FILTRU_INVALID 6

typedef struct Service {
	ListaOferte *lista;
	int(*validare)(Oferta*);
} Service;

Service* creeareService(ListaOferte *lista, int(*validare)(Oferta*));
void destroyElemsServ(Service* cont);
void distrugeService(Service* cont);
int getDimCont(Service *cont);
Vector* getAllElems(Service* cont);
ListaOferte* getLista(Service *cont);
int adaugare(Service *cont, int pret, int suprafata, char* adresa, char* tip);
Oferta* stergere(Service *cont, char* adresa, char* tip);
Oferta* actualizare(Service *cont, char* adresa, char* tip, int pret_nou, int suprafata_noua, char* adresa_noua, char* tip_nou);
Vector* filtrare(Service *cont, char* tip, int pret, int suprafata);
Vector* sortare(Service *cont, char* filtru);
void testService();