#pragma once
#include "repo.h"
#include "validator.h"
#define SORTAT 4
#define NESORTAT 5
#define FILTRU_INVALID 6

typedef struct Controller {
	ListaOferte *lista;
	int(*validare)(Oferta);
} Controller;

Controller creeareController(ListaOferte *lista, int(*validare)(Oferta));
int getDimCont(Controller *cont);
ListaOferte* getLista(Controller *cont);
int adaugare(Controller *cont, int pret, int suprafata, char adresa[50], char tip[50]);
int stergere(Controller *cont, char adresa[50], char tip[50]);
int actualizare(Controller *cont, char adresa[50], char tip[50], int pret_nou, int suprafata_noua, char adresa_noua[50], char tip_nou[50]);
ListaOferte filtrare(Controller *cont, char tip[50], int pret, int suprafata);
ListaOferte sortare(Controller *cont, char filtru[50]);
void testController();