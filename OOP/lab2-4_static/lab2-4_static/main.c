#include <stdio.h>
#include "ui.h"

int main() {
	testOferta();
	testRepo();
	testValidator();
	testController();
	ListaOferte lista = creeareLista();
	Controller cont = creeareController(&lista, validator);
	UI ui = creeareUI(&cont);
	meniu(&ui);
	return 0;
}