#define _CRT_SECURE_NO_WARNINGS
#pragma once
#include <stdlib.h>
#include <string.h>
#include <assert.h>

typedef struct Oferta {
	int suprafata, pret;
	char *adresa, *tip;
} Oferta;

Oferta* creeareOferta(int pret, int suprafata, char *adresa, char *tip);
void distrugeOferta(Oferta* of);
Oferta* copyOferta(Oferta* of);
char* getAdresa(Oferta* of);
char* getTip(Oferta* of);
int getPret(Oferta* of);
int getSuprafata(Oferta* of);
void setAdresa(Oferta *of, char* adresa);
void setTip(Oferta *of, char* tip);
void setPret(Oferta *of, int pret);
void setSuprafata(Oferta *of, int suprafata);
int equalOferta(Oferta* of, Oferta* of1);
void testOferta();