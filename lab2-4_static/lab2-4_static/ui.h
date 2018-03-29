#define _CRT_SECURE_NO_WARNINGS
#pragma once
#include <stdio.h>
#include <stdlib.h>
#include "controller.h"

typedef struct UI {
	Controller *cont;
} UI;

UI creeareUI(Controller *cont);
void adaugareOferta(UI *ui);
void stergereOferta(UI *ui);
void modificareOferta(UI *ui);
void filtrareOferta(UI *ui);
void sortareOferta(UI *ui);
void meniu(UI *ui);