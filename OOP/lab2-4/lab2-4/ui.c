#include "ui.h"

UI* creeareUI(Service *cont)
{
	UI* ui = (UI*)malloc(sizeof(UI));
	ui->cont = cont;
	return ui;
}

void distrugeUI(UI* ui) {
	free(ui);
}

void afisareEroare(int cod_eroare) {
	switch (cod_eroare) {
	case OFERTA_EXISTA_DEJA: printf("\nEroare repo: -- Oferta exista deja\n"); break;
	case OFERTA_NU_EXISTA: printf("\nEroare repo: -- Oferta nu exista\n"); break;
	case STRING_VID: printf("\nEroare la validare: -- String vid.\n"); break;
	case TIP_INVALID: printf("\nEroare la validare: -- Tip invalid.\n"); break;
	case NUMAR_INVALID: printf("\nEroare la validare: -- Numar invalid\n"); break;
	default: break;
	}
}

void adaugareOferta(UI *ui)
{
	char pret_str[50], suprafata_str[50], adresa[50], tip[50];
	printf("Introduceti oferta:\n");
	printf("Pret: ");
	scanf("%s", pret_str);
	printf("Suprafata: ");
	scanf("%s", suprafata_str);
	printf("Adresa: ");
	scanf("%s", adresa);
	printf("Tip: ");
	scanf("%s", tip);
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\nPret Invalid.\n"); return; }
	if (suprafata == -1) { printf("\nSuprafata Invalida.\n"); return; }
	int eroare = adaugare(ui->cont, pret, suprafata, adresa, tip);
	afisareEroare(eroare);
}

void stergereOferta(UI *ui)
{
	char adresa[50], tip[50];
	printf("Introduceti adresa si tip.");
	printf("\nAdresa: ");
	scanf("%s", adresa);
	printf("\nTip: ");
	scanf("%s", tip);
	int eroare = (int)stergere(ui->cont, adresa, tip);
	afisareEroare(eroare);
}

void modificareOferta(UI *ui)
{
	char adresa_vechi[50], tip_vechi[50], pret_str[50], suprafata_str[50], adresa[50], tip[50];
	printf("Introduceti adresa si tip existente: \n");
	printf("Adresa: ");
	scanf("%s", adresa_vechi);
	printf("\nTip: ");
	scanf("%s", tip_vechi);
	printf("Introduceti oferta noua: \n");
	printf("Pret: ");
	scanf("%s", pret_str);
	printf("\nSuprafata: ");
	scanf("%s", suprafata_str);
	printf("\nAdresa: ");
	scanf("%s", adresa);
	printf("\nTip: ");
	scanf("%s", tip);
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\nPret Invalid.\n"); return; }
	if (suprafata == -1) { printf("\nSuprafata Invalida.\n"); return; }
	int eroare = (int)actualizare(ui->cont, adresa_vechi, tip_vechi, pret, suprafata, adresa, tip);
	afisareEroare(eroare);
}

void afisareLista(Vector* lista) {
	Oferta* of;
	for (int i = 0; i < getDim(lista); ++i) {
		of = getElem(lista, i);

		printf("%d, %d, %s, %s\n", getPret(of), getSuprafata(of), getAdresa(of), getTip(of));
	}
	destroyElems(lista);
	distrugeVector(lista);
}

void filtrareOferta(UI *ui)
{
	char pret_str[50], suprafata_str[50], tip[50];
	printf("Introduceti filtru: \n");
	printf("Pret: ");
	scanf("%s", pret_str);
	printf("\nSuprafata: ");
	scanf("%s", suprafata_str);
	printf("\nTip: ");
	scanf("%s", tip);
	printf("\n");
	int pret = atoi(pret_str);
	int suprafata = atoi(suprafata_str);
	if (pret == -1) { printf("\nPret Invalid.\n"); return; }
	if (suprafata == -1) { printf("\nSuprafata Invalida.\n"); return; }
	Vector* lista_f = filtrare(ui->cont, tip, pret, suprafata);
	afisareLista(lista_f);
}

void sortareOferta(UI *ui)
{
	char filtru[50];
	printf("Introduceti filtru: ");
	scanf("%s", filtru);
	Vector* lista_f = sortare(ui->cont, filtru);
	afisareLista(lista_f);
}

void meniu(UI *ui)
{
	printf("====== Meniu ======\n");
	printf("1. Adaugare Oferta.\n");
	printf("2. Stergere Oferta.\n");
	printf("3. Modificare Oferta.\n");
	printf("4. Sortare Oferte.\n");
	printf("5. Filtrare Oferte.\n");
	printf("6. Afisare Oferte.\n");
	printf("0. Iesire.\n");
	char comanda[255];
	while (1) {
		printf("Introduceti comanda: ");
		scanf("%s", comanda);
		if (*comanda == '1') adaugareOferta(ui);
		else if (*comanda == '2') stergereOferta(ui);
		else if (*comanda == '3') modificareOferta(ui);
		else if (*comanda == '4') sortareOferta(ui);
		else if (*comanda == '5') filtrareOferta(ui);
		else if (*comanda == '6') afisareLista(getAllElems(ui->cont));
		else if (*comanda == '0') break;
		else printf("\nComanda Invalida\n");
	}
}
