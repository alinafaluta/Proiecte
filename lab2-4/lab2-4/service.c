#include "service.h"

Service* creeareService(ListaOferte *lista, int(*validare)(Oferta*))
{
	Service* cont = (Service*)malloc(sizeof(Service));
	cont->lista = lista;
	cont->validare = validare;
	return cont;
}

void destroyElemsServ(Service* cont) {
	destroyElemsRepo(cont->lista);
}

void distrugeService(Service* cont) {
	distrugeLista(cont->lista);
	free(cont);
}

int getDimCont(Service *cont)
{
	return getDimList(cont->lista);
}

Vector* getAllElems(Service* cont) {
	return getVect(cont->lista);
}

ListaOferte* getLista(Service* cont)
{
	return cont->lista;
}

int adaugare(Service *cont, int pret, int suprafata, char* adresa, char* tip)
{
	Oferta* of = creeareOferta(pret, suprafata, adresa, tip);
	int val = cont->validare(of);
	if (val) {
		distrugeOferta(of);
		return val;
	}
	val = adaugaOferta(cont->lista, of);
	if (val) {
		distrugeOferta(of);
		return val;
	}
	return SUCCES;
}

Oferta* stergere(Service *cont, char* adresa, char* tip)
{
	Oferta* of = creeareOferta(1, 1, adresa, tip);
	int val = cont->validare(of);
	if (val) {
		distrugeOferta(of);
		return (Oferta*)val;
	}
	Oferta* to_delete = stergeOferta(cont->lista, of);
	distrugeOferta(of);
	return to_delete;
}

Oferta* actualizare(Service *cont, char* adresa, char* tip, int pret_nou, int suprafata_noua, char* adresa_noua, char* tip_nou)
{
	Oferta* of_nou = creeareOferta(pret_nou, suprafata_noua, adresa_noua, tip_nou);
	Oferta* of_vechi = creeareOferta(1, 1, adresa, tip);
	int val = cont->validare(of_vechi);
	if (val) {
		distrugeOferta(of_nou);
		distrugeOferta(of_vechi);
		return (Oferta*)val;
	}
	val = cont->validare(of_nou);
	if (val) {
		distrugeOferta(of_nou);
		distrugeOferta(of_vechi);
		return (Oferta*)val;
	}
	Oferta* to_delete = modificaOferta(cont->lista, of_vechi, of_nou);
	distrugeOferta(of_nou);
	distrugeOferta(of_vechi);
	return to_delete;
}

Vector* filtrare(Service *cont, char* tip, int pret, int suprafata)
{
	Oferta* of;
	char* tip_temp;
	Vector* lista_filtrata = creeareVector(0, equalOferta, copyOferta, distrugeOferta);

	for (int i = 0; i < getDimCont(cont); ++i) {
		of = getOferta(cont->lista, i);
		tip_temp = getTip(of);
		if (strlen(tip)) if (strcmp(tip, tip_temp)) continue;
		if (pret > 0) if (getPret(of) != pret) continue;
		if (suprafata > 0) if (getSuprafata(of) != suprafata) continue;
		adaugaElem(lista_filtrata, of);
	}
	return lista_filtrata;
}

void swapOferte(Vector* lista, int poz, int poz1) {
	Oferta* of1 = getElem(lista, poz1);
	Oferta* of = setElem(lista, poz, of1);
	setElem(lista, poz1, of);
}

int verificaSortare(Vector* vect, char* filtru) {
	if (strcmp(filtru, "tip") && strcmp(filtru, "pret")) return FILTRU_INVALID;
	Oferta *of, *of1;
	for (int i = 0; i < getDim(vect) - 1; ++i) {
		of = getElem(vect, i);
		of1 = getElem(vect, i + 1);
		if (!strcmp(filtru, "tip"))
		{
			char* tip = getTip(of);
			char* tip1 = getTip(of1);
			if (strcmp(tip, tip1) > 0) return NESORTAT;
		}
		else if (!strcmp(filtru, "pret")) if (getPret(of) > getPret(of1)) return NESORTAT;
	}
	return SORTAT;
}

Vector* sortare(Service *cont, char* filtru)
{
	Vector* lista_sortata = getVect(cont->lista);
	Oferta *of, *of1;
	char *tip, *tip1;
	for (int i = 0; i < getDim(lista_sortata) - 1; ++i) {
		for (int j = i + 1; j < getDim(lista_sortata); ++j) {
			of = getElem(lista_sortata, i);
			of1 = getElem(lista_sortata, j);
			if (!strcmp(filtru, "tip")) {
				tip = getTip(of);
				tip1 = getTip(of1);
				if (strcmp(tip1, tip) < 0) swapOferte(lista_sortata, i, j);
			}
			else if (!strcmp(filtru, "pret")) {
				if (getPret(of1) < getPret(of)) swapOferte(lista_sortata, i, j);
			}
			else return lista_sortata;
		}
	}
	return lista_sortata;
}

void testService()
{
	ListaOferte* list = creeareLista();
	Vector* list_f;
	Service* cont = creeareService(list, validator);
	assert(adaugare(cont, 10, 10, "adresaTestController", "casa") == SUCCES);
	assert(adaugare(cont, 30, 20, "adresaTestController1", "apartament") == SUCCES);
	assert(adaugare(cont, 10, 10, "adresaTestController", "casa") == OFERTA_EXISTA_DEJA);
	assert(adaugare(cont, 10, 10, "adresaTestController", "tipTestController") == TIP_INVALID);
	assert(getDimCont(cont) == 2);
	Oferta* temp = actualizare(cont, "adresaTestController", "casa", 10, 10, "adresaTestControllerModificata", "teren");
	assert((int)temp != OFERTA_NU_EXISTA);
	Oferta* temp1 = actualizare(cont, "adresaTestController", "casa", 10, 10, "adresaTestControllerModificata1", "apartament");
	assert((int)temp1 == OFERTA_NU_EXISTA);
	Oferta* temp2 = stergere(cont, "adresaTestController1", "apartament");
	assert((int)temp2 != OFERTA_NU_EXISTA);
	Oferta* temp3 = stergere(cont, "adresaTestController1", "apartament");
	assert((int)temp3 == OFERTA_NU_EXISTA);
	assert(adaugare(cont, 10, 10, "adresaTestController1", "apartament") == SUCCES);
	assert(adaugare(cont, 1, 100, "adresaTestController2", "casa") == SUCCES);
	list_f = filtrare(cont, "casa", 0, 0);
	assert(getDim(list_f) == 1);
	distrugeVector(list_f);
	list_f = filtrare(cont, "teren", 0, 10);
	assert(getDim(list_f) == 1);
	distrugeVector(list_f);
	list_f = filtrare(cont, "", 10, 10);
	assert(getDim(list_f) == 2);
	distrugeVector(list_f);
	list_f = sortare(cont, "suprafata");
	assert(verificaSortare(list_f, "suprafata") == FILTRU_INVALID);
	destroyElems(list_f);
	distrugeVector(list_f);
	list_f = sortare(cont, "pret");
	assert(verificaSortare(list_f, "pret") == SORTAT);
	destroyElems(list_f);
	distrugeVector(list_f);
	list_f = sortare(cont, "tip");
	assert(verificaSortare(list_f, "pret") == NESORTAT);
	assert(verificaSortare(list_f, "tip") == SORTAT);
	destroyElems(list_f);
	distrugeVector(list_f);
	ListaOferte* copie_lista = getLista(cont);
	Vector* copie_vector = getAllElems(cont);
	destroyElems(copie_vector);
	distrugeVector(copie_vector);
	Oferta *poz_c, *poz;
	for (int i = 0; i < getDimList(copie_lista); ++i) {
		poz_c = getOferta(copie_lista, i);
		poz = getOferta(cont->lista, i);
		assert(equalOferta(poz_c, poz));
	}
	distrugeOferta(temp);
	distrugeOferta(temp2);
	destroyElemsRepo(cont->lista);
	distrugeService(cont);
}