#include "repo.h"
#include <stdio.h>

ListaOferte* creeareLista()
{
	ListaOferte* list = (ListaOferte*)malloc(sizeof(ListaOferte));
	list->vect = creeareVector(0, equalOferta, copyOferta, distrugeOferta);
	return list;
}

void destroyElemsRepo(ListaOferte* lista) {
	destroyElems(lista->vect);
}

void distrugeLista(ListaOferte* lista)
{
	distrugeVector(lista->vect);
	free(lista);
}

int getDimList(ListaOferte *lista)
{
	return getDim(lista->vect);
}

Vector* getVect(ListaOferte *lista) {
	return copyVector(lista->vect, distrugeOferta);
}

int adaugaOferta(ListaOferte* lista, Oferta* of)
{
	int poz = cautaOferta(lista, of);
	if (poz != -1) return OFERTA_EXISTA_DEJA;
	adaugaElem(lista->vect, of);
	return SUCCES;
}

Oferta* stergeOferta(ListaOferte* lista, Oferta* of)
{
	int poz = cautaOferta(lista, of);
	if (poz == -1) return (Oferta*)OFERTA_NU_EXISTA;
	return stergeElem(lista->vect, of);
}

Oferta* modificaOferta(ListaOferte *lista, Oferta* of_vechi, Oferta* of_nou)
{
	int poz = cautaOferta(lista, of_vechi);
	if (poz == -1) return (Oferta*)OFERTA_NU_EXISTA;
	Oferta* to_delete = setElem(lista->vect, poz, copyOferta(of_nou));
	return to_delete;
}

Oferta* setOferta(ListaOferte *lista, int poz, Oferta* of_nou)
{
	if (poz < 0 || poz > getDim(lista->vect)) return (Oferta*)OFERTA_NU_EXISTA;
	Oferta* to_delete = setElem(lista->vect, poz, of_nou);
	return to_delete;
}

int cautaOferta(ListaOferte* lista, Oferta* of)
{
	return cautaElem(lista->vect, of);
}

Oferta* getOferta(ListaOferte *lista, int poz)
{
	return getElem(lista->vect, poz);
}

void testRepo()
{
	ListaOferte* list = creeareLista(0);
	assert(getDimList(list) == 0);
	Oferta* of = creeareOferta(10, 20, "Adresaof", "Tipof");
	Oferta* of1 = creeareOferta(20, 30, "Adresaof1", "Tipof1");
	assert(adaugaOferta(list, of) == SUCCES);
	assert(adaugaOferta(list, of1) == SUCCES);
	assert(adaugaOferta(list, of1) == OFERTA_EXISTA_DEJA);
	assert(equalOferta(getOferta(list, 0), of));
	assert(equalOferta(getOferta(list, 1), of1));
	assert(cautaOferta(list, of) == 0);
	assert(getDimList(list) == 2);
	Oferta* to_delete = stergeOferta(list, of);
	assert((int)to_delete != OFERTA_NU_EXISTA);
	Oferta* not_found = stergeOferta(list, of);
	assert((int)not_found == OFERTA_NU_EXISTA);
	assert(cautaOferta(list, of) == OFERTA_NU_EXISTA);
	Oferta* temp2 = modificaOferta(list, of, of1);
	assert((int)temp2 == OFERTA_NU_EXISTA);
	Oferta* temp4 = modificaOferta(list, of1, of);
	assert((int)temp4 != OFERTA_NU_EXISTA);
	Oferta* temp3 = setOferta(list, 3, of);
	assert((int)temp3 == OFERTA_NU_EXISTA);
	Oferta* to_delete2 = setOferta(list, 0, of1);
	assert((int)to_delete2 != OFERTA_NU_EXISTA);
	Vector* copieVect = getVect(list);
	destroyElems(copieVect);
	distrugeVector(copieVect);
	distrugeOferta(to_delete);
	distrugeOferta(to_delete2);
	destroyElemsRepo(list);
	distrugeLista(list);
}
