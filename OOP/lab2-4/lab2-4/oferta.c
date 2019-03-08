#include "oferta.h"

Oferta* creeareOferta(int pret, int suprafata, char* adresa, char* tip)
{
	Oferta* of = (Oferta*)malloc(sizeof(Oferta));
	of->pret = pret;
	of->suprafata = suprafata;
	of->adresa = _strdup(adresa);
	of->tip = _strdup(tip);
	return of;
}

void distrugeOferta(Oferta* of) {
	free(of->adresa);
	free(of->tip);
	free(of);
}

char* getAdresa(Oferta* of){
	return of->adresa;
}

char* getTip(Oferta* of){
	return of->tip;
}

int getPret(Oferta* of)
{
	return of->pret;
}

int getSuprafata(Oferta* of)
{
	return of->suprafata;
}

void setAdresa(Oferta *of, char* adresa)
{
	free(of->adresa);
	of->adresa = _strdup(adresa);
}

void setTip(Oferta *of, char* tip)
{
	free(of->tip);
	of->tip = _strdup(tip);
}

void setPret(Oferta *of, int pret)
{
	of->pret = pret;
}

void setSuprafata(Oferta *of, int suprafata)
{
	of->suprafata = suprafata;
}

Oferta* copyOferta(Oferta *of) {
	Oferta* of_copy = creeareOferta(getPret(of), getSuprafata(of), getAdresa(of), getTip(of));
	return of_copy;
}

int equalOferta(Oferta* of, Oferta* of1)
{
	if (strcmp(of->tip, of1->tip)) return 0;
	if (strcmp(of->adresa, of1->adresa)) return 0;
	return 1;
}

void testOferta(){
	int pret = 10;
	int suprafata = 20;
	char* adresa = _strdup("AdresaTest");
	char* tip = _strdup("TipTest");
	Oferta* of = creeareOferta(pret, suprafata, adresa, tip);
	Oferta* of2 = of;
	Oferta* of3 = copyOferta(of);
	assert(getPret(of) == pret);
	assert(getSuprafata(of) == suprafata);
	assert(strcmp(getAdresa(of), adresa) == 0);
	assert(strcmp(getTip(of), tip) == 0);
	pret = 200;
	suprafata = 1000;
	setPret(of, pret);
	setSuprafata(of, suprafata);
	assert(getPret(of) == pret);
	assert(getSuprafata(of) == suprafata);
	Oferta* of1 = creeareOferta(pret, suprafata, adresa, tip);
	free(tip);
	free(adresa);
	adresa = _strdup("Adresa2test");
	tip = _strdup("Tip2test");
	setAdresa(of, adresa);
	setTip(of, tip);
	assert(!equalOferta(of, of1));
	assert(!equalOferta(of, of3));
	assert(equalOferta(of, of2));
	free(tip);
	free(adresa);
	distrugeOferta(of);
	distrugeOferta(of1);
	distrugeOferta(of3);
}