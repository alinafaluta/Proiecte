#include "Teste.h"
#include "UI.h"
#include <stdio.h>
#include <stdlib.h>
int main()
{
	test();
	ListaPersoane lista = makeList();
	Service serv = creeareService(&lista);
	UI ui = creeareUi(&serv);
	start(&ui);
	return 0;
}