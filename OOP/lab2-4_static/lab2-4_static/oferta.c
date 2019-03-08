#include "oferta.h"

Oferta creeareOferta(int pret, int suprafata, char adresa[50], char tip[50])
{
	Oferta of;
	of.pret = pret;
	of.suprafata = suprafata;
	strcpy(of.adresa, adresa);
	strcpy(of.tip, tip);
	return of;
}

void getAdresa(Oferta of, char adresa_out[50]){
	strcpy(adresa_out, of.adresa);
}

void getTip(Oferta of, char tip_out[50]){
	strcpy(tip_out, of.tip);
}

int getPret(Oferta of)
{
	return of.pret;
}

int getSuprafata(Oferta of)
{
	return of.suprafata;
}

void setAdresa(Oferta *of, char adresa[50])
{
	strcpy(of->adresa, adresa);
}

void setTip(Oferta *of, char tip[50])
{
	strcpy(of->tip, tip);
}

void setPret(Oferta *of, int pret)
{
	of->pret = pret;
}

void setSuprafata(Oferta *of, int suprafata)
{
	of->suprafata = suprafata;
}

bool equalOferta(Oferta of, Oferta of1)
{
	if (strcmp(of.tip, of1.tip)) return false;
	if (strcmp(of.adresa, of1.adresa)) return false;
	return true;
}

void testOferta(){
	int pret = 10;
	int suprafata = 20;
	char adresa[50] = "AdresaTest";
	char tip[50] = "TipTest";
	Oferta of = creeareOferta(pret, suprafata, adresa, tip);
	Oferta of2 = of;
	char adresa_out[50], tip_out[50];
	getAdresa(of, adresa_out);
	getTip(of, tip_out);
	assert(getPret(of) == pret);
	assert(getSuprafata(of) == suprafata);
	assert(strcmp(adresa_out, adresa) == 0);
	assert(strcmp(tip_out, tip) == 0);
	pret = 200;
	suprafata = 1000;
	setPret(&of, pret);
	setSuprafata(&of, suprafata);
	assert(getPret(of) == pret);
	assert(getSuprafata(of) == suprafata);
	strcpy(adresa, "Adresa2test");
	strcpy(tip, "Tip2test");
	setAdresa(&of, adresa);
	setTip(&of, tip);
	Oferta of1 = creeareOferta(pret, suprafata, adresa, tip);
	assert(equalOferta(of, of1));
	assert(!equalOferta(of, of2));
}