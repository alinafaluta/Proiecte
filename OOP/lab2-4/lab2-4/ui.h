#pragma once
#include <stdio.h>
#include <stdlib.h>
#include "service.h"

typedef struct UI {
	Service *cont;
} UI;

UI* creeareUI(Service *cont);
void distrugeUI(UI *ui);
void adaugareOferta(UI *ui);
void stergereOferta(UI *ui);
void modificareOferta(UI *ui);
void filtrareOferta(UI *ui);
void sortareOferta(UI *ui);
void meniu(UI *ui);