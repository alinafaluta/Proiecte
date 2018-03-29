#include "controller.h"

Controller creeareController(ListaOferte *lista, int(*validare)(Oferta))
{
	Controller cont;
	cont.lista = lista;
	cont.validare = validare;
	return cont;
}

int getDimCont(Controller *cont)
{
	return getDim(cont->lista);
}

ListaOferte* getLista(Controller *cont)
{
	return cont->lista;
}

int adaugare(Controller *cont, int pret, int suprafata, char adresa[50], char tip[50])
{
	Oferta of = creeareOferta(pret, suprafata, adresa, tip);
	int val = cont->validare(of);
	if (val) return val;
	val = adaugaOferta(cont->lista, of);
	if (val) return val;
	return SUCCES;
}

int stergere(Controller *cont, char adresa[50], char tip[50])
{
	Oferta of = creeareOferta(1, 1, adresa, tip);
	int val = cont->validare(of);
	if (val) return val;
	val = stergeOferta(cont->lista, of);
	if (val) return val;
	return SUCCES;
}

int actualizare(Controller *cont, char adresa[50], char tip[50], int pret_nou, int suprafata_noua, char adresa_noua[50], char tip_nou[50])
{
	Oferta of_nou = creeareOferta(pret_nou, suprafata_noua, adresa_noua, tip_nou);
	Oferta of_vechi = creeareOferta(1, 1, adresa, tip);
	int val = cont->validare(of_vechi);
	if (val) return val;
	val = cont->validare(of_nou);
	if (val) return val;
	val = modificaOferta(cont->lista, of_vechi, of_nou);
	if (val) return val;
	return SUCCES;
}

ListaOferte filtrare(Controller *cont, char tip[50], int pret, int suprafata)
{
	Oferta of;
	char tip_temp[50];
	ListaOferte lista_filtrata = creeareLista();

	for (int i = 0; i < getDimCont(cont); ++i) {
		of = getOferta(cont->lista, i);
		getTip(of, tip_temp);
		if (strlen(tip)) if (strcmp(tip, tip_temp)) continue;
		if (pret > 0) if (getPret(of) != pret) continue;
		if (suprafata > 0) if (getSuprafata(of) != suprafata) continue;
		adaugaOferta(&lista_filtrata, of);
	}
	return lista_filtrata;
}

void swapOferte(ListaOferte *lista, Oferta of, Oferta of1, int poz, int poz1) {
	setOferta(lista, poz1, of);
	setOferta(lista, poz, of1);
}

int verificaSortare(ListaOferte *lista, char filtru[50]) {
	if (strcmp(filtru, "tip") && strcmp(filtru, "pret")) return FILTRU_INVALID;
	Oferta of, of1;
	for (int i = 0; i < getDim(lista) - 1; ++i) {
		of = getOferta(lista, i);
		of1 = getOferta(lista, i + 1);
		if (!strcmp(filtru, "tip"))
		{
			char tip[50], tip1[50];
			getTip(of, tip);
			getTip(of1, tip1);
			if (strcmp(tip, tip1) > 0) return NESORTAT;
		}
		else if (!strcmp(filtru, "pret")) if (getPret(of) > getPret(of1)) return NESORTAT;
	}
	return SORTAT;
}

ListaOferte sortare(Controller *cont, char filtru[50])
{
	ListaOferte lista_sortata = creeareLista();
	Oferta of, of1;
	char tip[50], tip1[50];
	for (int i = 0; i < getDimCont(cont); ++i) {
		adaugaOferta(&lista_sortata, getOferta(cont->lista, i));
	}
	for (int i = 0; i < getDim(&lista_sortata) - 1; ++i) {
		for (int j = i + 1; j < getDim(&lista_sortata); ++j) {
			of = getOferta(&lista_sortata, i);
			of1 = getOferta(&lista_sortata, j);
			if (!strcmp(filtru, "tip")) {
				getTip(of, tip);
				getTip(of1, tip1);
				if (strcmp(tip1, tip) < 0) swapOferte(&lista_sortata, of, of1, i, j);
			}
			else if (!strcmp(filtru, "pret")) {
				if (getPret(of1) < getPret(of)) swapOferte(&lista_sortata, of, of1, i, j);
			}
			else return lista_sortata;
		}
	}
	return lista_sortata;
}

void testController()
{
	ListaOferte list = creeareLista();
	ListaOferte list_f;
	Controller cont = creeareController(&list, validator);
	assert(adaugare(&cont, 10, 10, "adresaTestController", "casa") == SUCCES);
	assert(adaugare(&cont, 30, 20, "adresaTestController1", "apartament") == SUCCES);
	assert(adaugare(&cont, 10, 10, "adresaTestController", "casa") == OFERTA_EXISTA_DEJA);
	assert(adaugare(&cont, 10, 10, "adresaTestController", "tipTestController") == TIP_INVALID);
	assert(getDimCont(&cont) == 2);
	assert(actualizare(&cont, "adresaTestController", "casa", 10, 10, "adresaTestControllerModificata", "teren") == SUCCES);
	assert(actualizare(&cont, "adresaTestController", "casa", 10, 10, "adresaTestControllerModificata1", "apartament") == OFERTA_NU_EXISTA);
	assert(stergere(&cont, "adresaTestController1", "apartament") == SUCCES);
	assert(stergere(&cont, "adresaTestController1", "apartament") == OFERTA_NU_EXISTA);
	assert(adaugare(&cont, 10, 10, "adresaTestController1", "apartament") == SUCCES);
	assert(adaugare(&cont, 1, 100, "adresaTestController2", "casa") == SUCCES);
	list_f = filtrare(&cont, "casa", 0, 0);
	assert(getDim(&list_f) == 1);
	list_f = filtrare(&cont, "teren", 0, 10);
	assert(getDim(&list_f) == 1);
	list_f = filtrare(&cont, "", 10, 10);
	assert(getDim(&list_f) == 2);
	list_f = sortare(&cont, "suprafata");
	assert(verificaSortare(&list_f, "suprafata") == FILTRU_INVALID);
	list_f = sortare(&cont, "pret");
	assert(verificaSortare(&list_f, "pret") == SORTAT);
	list_f = sortare(&cont, "tip");
	assert(verificaSortare(&list_f, "pret") == NESORTAT);
	assert(verificaSortare(&list_f, "tip") == SORTAT);
	ListaOferte* copie_lista = getLista(&cont);
	for (int i = 0; i < getDim(copie_lista); ++i) {
		assert(equalOferta(getOferta(copie_lista, i), getOferta(cont.lista, i)));
	}
}