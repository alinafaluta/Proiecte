#include "ui.h"

UI creeareUI(Controller *cont)
{
	UI ui;
	ui.cont = cont;
	return ui;
}

void afisareeroare(int cod_eroare) {
	switch (cod_eroare) {
	case OFERTA_EXISTA_DEJA: printf("\neroare repo: -- oferta exista deja\n"); break;
	case OFERTA_NU_EXISTA: printf("\neroare repo: -- oferta nu exista\n"); break;
	case STRING_VID: printf("\neroare la validare: -- string vid.\n"); break;
	case TIP_INVALID: printf("\neroare la validare: -- tip invalid.\n"); break;
	case NUMAR_INVALID: printf("\neroare la validare: -- numar invalid\n"); break;
	default: break;
	}
}

void adaugareOferta(UI *ui)
{
	char pret_str[50], suprafata_str[50], adresa[50], tip[50];
	printf("introduceti oferta:\n");
	printf("pret: ");
	scanf("%s", pret_str);
	printf("suprafata: ");
	scanf("%s", suprafata_str);
	printf("adresa: ");
	scanf("%s", adresa);
	printf("tip: ");
	scanf("%s", tip);
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\npret invalid.\n"); return; }
	if (suprafata == -1) { printf("\nsuprafata invalida.\n"); return; }
	int eroare = adaugare(ui->cont, pret, suprafata, adresa, tip);
	afisareeroare(eroare);
}

void stergereOferta(UI *ui)
{
	char adresa[50], tip[50];
	printf("introduceti adresa si tip.");
	printf("\nadresa: ");
	scanf("%s", adresa);
	printf("\ntip: ");
	scanf("%s", tip);
	int eroare = stergere(ui->cont, adresa, tip);
	afisareeroare(eroare);
}

void modificareOferta(UI *ui)
{
	char adresa_vechi[50], tip_vechi[50], pret_str[50], suprafata_str[50], adresa[50], tip[50];
	printf("introduceti adresa si tip existente: \n");
	printf("adresa: ");
	scanf("%s", adresa_vechi);
	printf("\ntip: ");
	scanf("%s", tip_vechi);
	printf("introduceti oferta noua: \n");
	printf("pret: ");
	scanf("%s", pret_str);
	printf("\nsuprafata: ");
	scanf("%s", suprafata_str);
	printf("\nadresa: ");
	scanf("%s", adresa);
	printf("\ntip: ");
	scanf("%s", tip);
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\npret invalid.\n"); return; }
	if (suprafata == -1) { printf("\nsuprafata invalida.\n"); return; }
	int eroare = actualizare(ui->cont, adresa_vechi, tip_vechi, pret, suprafata, adresa, tip);
	afisareeroare(eroare);
}

void afisareLista(ListaOferte *lista) {
	char tip[50], adresa[50];
	Oferta of;
	for (int i = 0; i < getDim(lista); ++i) {
		of = getOferta(lista, i);
		getAdresa(of, adresa);
		getTip(of, tip);
		printf("%d, %d, %s, %s\n", getPret(of), getSuprafata(of), adresa, tip);
	}
}

void filtrareOferta(UI *ui)
{
	char pret_str[50], suprafata_str[50], tip[50];
	printf("introduceti filtru: \n");
	printf("pret: ");
	scanf("%s", pret_str);
	printf("\nsuprafata: ");
	scanf("%s", suprafata_str);
	printf("\ntip: ");
	scanf("%s", tip);
	printf("\n");
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\npret invalid.\n"); return; }
	if (suprafata == -1) { printf("\nsuprafata invalida.\n"); return; }
	ListaOferte lista_f = filtrare(ui->cont, tip, pret, suprafata);
	afisareLista(&lista_f);
}

void sortareOferta(UI *ui)
{
	char filtru[50];
	printf("introduceti filtru: ");
	scanf("%s", filtru);
	ListaOferte lista_f = sortare(ui->cont, filtru);
	afisareLista(&lista_f);
}

void meniu(UI *ui)
{
	printf("====== meniu ======\n");
	printf("1. adaugare oferta.\n");
	printf("2. stergere oferta.\n");
	printf("3. modificare oferta.\n");
	printf("4. sortare oferte.\n");
	printf("5. filtrare oferte.\n");
	printf("6. afisare oferte.\n");
	printf("0. iesire.\n");
	char comanda[10];
	while (1) {
		printf("introduceti comanda: ");
		scanf("%s", comanda);
		if (comanda[0] == '1') adaugareOferta(ui);
		else if (comanda[0] == '2') stergereOferta(ui);
		else if (comanda[0] == '3') modificareOferta(ui);
		else if (comanda[0] == '4') sortareOferta(ui);
		else if (comanda[0] == '5') filtrareOferta(ui);
		else if (comanda[0] == '6') afisareLista(getLista(ui->cont));
		else if (comanda[0] == '0') break;
		else printf("\ncomanda invalida\n");
	}
}
