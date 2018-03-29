#include "repo.h"

ListaOferte creeareLista()
{
	ListaOferte list;
	list.dim = 0;
	return list;
}

int getDim(ListaOferte *lista)
{
	return lista->dim;
}

int adaugaOferta(ListaOferte *lista, Oferta of)
{
	int poz = cautaOferta(lista, of);
	if (poz != -1) return OFERTA_EXISTA_DEJA;
	lista->oferte[lista->dim] = of;
	lista->dim++;
	return SUCCES;
}

int stergeOferta(ListaOferte *lista, Oferta of)
{
	int poz = cautaOferta(lista, of);
	if (poz == -1) return OFERTA_NU_EXISTA;
	for (int i = poz; i < lista->dim - 1; ++i) {
		lista->oferte[i] = lista->oferte[i + 1];
	}
	lista->dim--;
	return SUCCES;
}

int modificaOferta(ListaOferte *lista, Oferta of_vechi, Oferta of_nou)
{
	int poz = cautaOferta(lista, of_vechi);
	if (poz == -1) return OFERTA_NU_EXISTA;
	lista->oferte[poz] = of_nou;
	return SUCCES;
}

int setOferta(ListaOferte *lista, int poz, Oferta of_nou)
{
	if (poz < 0 || poz > getDim(lista)) return OFERTA_NU_EXISTA;
	lista->oferte[poz] = of_nou;
	return SUCCES;
}

int cautaOferta(ListaOferte *lista, Oferta of)
{
	for (int i = 0; i < lista->dim; ++i) {
		if (equalOferta(of, getOferta(lista, i))) return i;
	}
	return OFERTA_NU_EXISTA;
}

Oferta getOferta(ListaOferte *lista, int poz)
{
	return lista->oferte[poz];
}

void testRepo()
{
	ListaOferte list = creeareLista(0);
	assert(getDim(&list) == 0);
	Oferta of = creeareOferta(10, 20, "Adresaof", "Tipof");
	Oferta of1 = creeareOferta(20, 30, "Adresaof1", "Tipof1");
	adaugaOferta(&list, of);
	assert(adaugaOferta(&list, of1) == SUCCES);
	assert(adaugaOferta(&list, of1) == OFERTA_EXISTA_DEJA);
	assert(equalOferta(getOferta(&list, 0), of));
	assert(equalOferta(getOferta(&list, 1), of1));
	assert(cautaOferta(&list, of) == 0);
	assert(getDim(&list) == 2);
	assert(stergeOferta(&list, of) == SUCCES);
	assert(stergeOferta(&list, of) == OFERTA_NU_EXISTA);
	assert(cautaOferta(&list, of) == OFERTA_NU_EXISTA);
	assert(modificaOferta(&list, of, of1) == OFERTA_NU_EXISTA);
	assert(modificaOferta(&list, of1, of) == SUCCES);
	assert(setOferta(&list, 3, of) == OFERTA_NU_EXISTA);
	assert(setOferta(&list, 0, of1) == SUCCES);
}
