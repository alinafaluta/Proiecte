#pragma once

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include "Service.h"
#include "Repo.h"

typedef struct UI {
	Service *serv;
} UI;
UI creeareUi(Service* s);
void start(UI* ui);
void meniu();
void comanda1(UI* ui);
void comanda2(UI* ui);
void comanda3(UI* ui);
void comanda4(UI* ui);
void comanda5(UI* ui);
void comanda6(UI* ui);
void comanda7(UI* ui);
void comanda8(UI* ui);
void afisareeroare(int cod_eroare);
