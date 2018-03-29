#define _CRT_SECURE_NO_WARNINGS
#pragma once
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <assert.h>

typedef struct Oferta {
	int suprafata, pret;
	char adresa[50], tip[50];
} Oferta;

Oferta creeareOferta(int pret, int suprafata, char adresa[50], char tip[50]);
void getAdresa(Oferta of, char adresa_out[50]);
void getTip(Oferta of, char tip_out[50]);
int getPret(Oferta of);
int getSuprafata(Oferta of);
void setAdresa(Oferta* of, char adresa_out[50]);
void setTip(Oferta* of, char tip_out[50]);
void setPret(Oferta* of, int pret);
void setSuprafata(Oferta* of, int suprafata);
bool equalOferta(Oferta of, Oferta of1);
void testOferta();