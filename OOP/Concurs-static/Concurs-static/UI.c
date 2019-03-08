
#include "UI.h"
#include "Service.h"
#include "Repo.h"
#include "Validare.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void start(UI* ui)
{

	meniu();
	char cmd[255] = "z";
	while (1)
	{
		printf("\n Dati comanda: ");
		scanf("%s", cmd);
		if (*cmd == '1')
		{
			comanda1(ui);

		}
		else if (*cmd == '2')
		{
			comanda2(ui);

		}
		else if (*cmd == '3')
		{
			comanda3(ui);
		}
		else if (*cmd == '4')
		{
			comanda4(ui);
		}
		else if (*cmd == '5')
		{
			comanda5(ui);
		}
		else if (*cmd == '6')
		{
			comanda6(ui);
		}
		else if (*cmd == '7')
		{
			comanda7(ui);
		}
		else if (*cmd == '8')
		{
			comanda8(ui);
		}
		if (*cmd == 'x')
			break;

	}
	if (*cmd == 'x')
		printf("\nBYE\n");
}

void meniu()
{
	printf(" =============================Concurs Informatica=============================\n");
	printf("| 1. Adaugare participant                                                     |\n");
	printf("| 2. Vizualizare lista de participanti                                        |\n");
	printf("| 3. Sterge partcipant                                                        |\n");
	printf("| 4. Actualizare participanti                                                 |\n");
	printf("| 5. Filtrare participanti dupa scor mai mic de cat o valoare data            |\n");
	printf("| 6. Filtrare participanti a caror nume incep cu o litera data                |\n");
	printf("| 7. Sortare crescatoare sau descrescatoare dupa scor                         |\n");
	printf("| 8. Sortare crescatoare sau descrescatoare dupa nume                         |\n");
	printf("| x. Exit                                                                     |\n");
	printf(" ============================================================================= \n");

}

UI creeareUi(Service* s)
{
	UI ui;
	ui.serv = s;
	return ui;
}
void afisareeroare(int cod_eroare) {
	switch (cod_eroare) {
	case PERSOANA_EXISTA_DEJA: 
		printf("\neroare repo: -- persoama exista deja\n");
		break;
	case PERSOANA_NU_EXISTA: 
		printf("\neroare repo: -- persoana nu exista\n"); 
		break;
	case NUME_VID:
		printf("\neroare la validare: -- nume vid.\n"); 
		break;
	case PRENUME_VID: 
		printf("\neroare la validare: -- prenume vid.\n"); 
		break;
	case SCOR_INVALID:
		printf("\neroare la validare: -- scor invalid.\n"); 
		break;
	default:
		break;
	}
}

void comanda1(UI* ui)
{
	char nume[20], prenume[20];
	char scor[10];
	printf("Dati numele concurentului: ");
	scanf("%s", &nume);

	printf("Dati prenumele concurentului: ");
	scanf("%s", &prenume);
	
	printf("Dati scorul concurentului: ");
	scanf("%s", &scor);
	int scoru = atoi(scor);
	if (scoru == -1) { printf("\nscor invalid.\n"); return; }
	int eroare = adaugare(ui->serv, 1, nume, prenume, scoru);
	afisareeroare(eroare);
	comanda2(ui);
}

void comanda2(UI* ui)
{
	if (getdim(ui->serv) == 0)
	{
		printf("\nLista Vida \n");
	}
	else
	{
		ListaPersoane *l = getLista(ui->serv);
		for (int i = 0; i < l->nr_pers; i++)
		{
			printf("ID: %d   NUME:%s    PRENUME: %s    SCOR:%d \n", l->lista[i].id, l->lista[i].nume, l->lista[i].prenume, l->lista[i].scor);
		}
	}
}
void comanda3(UI* ui)
{
	char nume[20], prenume[20];
	int id, scor;
	printf("introduceti datele.");
	printf("\nID: ");
	scanf("%d", &id);
	printf("\nnume: ");
	scanf("%s", nume);
	printf("\nprenume: ");
	scanf("%s", prenume);
	printf("\nScoe: ");
	scanf("%d", &scor);
	int eroare = stergere(ui->serv, id,nume, prenume,scor);
	afisareeroare(eroare);
}
void comanda4(UI* ui)
{
	char idu[10];
	char nume[20], prenume[20], numeNou[20], prenumeNou[20];
	char scor[10],scor_nou[10];
	printf("\nDatele concurentului pe care doriti sa il actualizati: \n");
	printf("ID:");
	scanf("%s", &idu);
	printf("Numele:");
	scanf("%s", &nume);
	printf("Prenumele:");
	scanf("%s", &prenume);
	printf("Scor:");
	scanf("%s", &scor);
	int sc = atoi(scor);
	int id = atoi(idu);
	if (id == -1)
	{
		printf("Id incorect\n");
		return;
	}
	if (sc == -1)
	{
		printf("Scor incorect\n");
		return;
	}
	printf("\nDatele concurentului cu care doriti sa il actualizati: \n");
	printf("Numele nou:");
	scanf("%s", &numeNou);
	printf("Prenumele nou:");
	scanf("%s", &prenumeNou);
	printf("Scor nou:");
	scanf("%s", scor_nou);
	ListaPersoane* l = getLista(ui->serv);
	Persoana p = creeaza(id, nume, prenume, sc);
	int scorul = atoi(scor_nou);
	if (sc == -1)
	{
		printf("Scor incorect\n");
		return;
	}
	int poz = find(l, p);
	if (poz == -1)
	{
		printf("Persoana nu exista");
	}
	else {
		int eroare = actualizare(ui->serv, id, nume, prenume, sc, numeNou, prenumeNou, scorul);
		afisareeroare(eroare);
	}
}

void comanda5(UI* ui)
{
	ListaPersoane list = makeList();
	int val;
	printf("Dati o valoare:");
	scanf("%d", &val);
	list = filtrareScorMic(ui->serv, val);
	if (list.nr_pers == 0)
	{
		printf("\nLista Vida \n");
	}
	else
	{
		for (int i = 0; i < list.nr_pers; i++)
		{
			printf("ID: %d   NUME:%s    PRENUME: %s    SCOR:%d \n", list.lista[i].id, list.lista[i].nume, list.lista[i].prenume, list.lista[i].scor);
		}
	}
}

void comanda6(UI* ui)
{
	ListaPersoane list = makeList();
	char litera[2];
	printf("Dati o litera: ");
	scanf("%s", litera);
	list = filtrareLitera(ui->serv, *litera);
	if (list.nr_pers == 0)
	{
		printf("\nLista Vida \n");
	}
	else
	{
		for (int i = 0; i < list.nr_pers; i++)
		{
			printf("ID: %d   NUME:%s    PRENUME: %s    SCOR:%d \n", list.lista[i].id, list.lista[i].nume, list.lista[i].prenume, list.lista[i].scor);
		}
	}

}

void comanda7(UI* ui)
{
	printf("        1. crescator \n");
	printf("        2. descrescator \n");
	printf("Alegeti sortarea: ");
	int cmd;
	scanf("%d", &cmd);
	if (cmd == 1)
	{
		sortareNume(ui->serv, 1);
		comanda2(ui);
	}
	else
		if (cmd == 2)
		{
			sortareNume(ui->serv, -1);
			comanda2(ui);
		}
}

void comanda8(UI* ui)
{
	printf("        1. crescator \n");
	printf("        2. descrescator \n");
	printf("Alegeti sortarea: ");
	int cmd;
	scanf("%d", &cmd);
	if (cmd == 1)
	{
		sortareScor(ui->serv, 1);
		comanda2(ui);
	}
	else
		if (cmd == 2)
		{
			sortareScor(ui->serv, -1);
			comanda2(ui);
		}
}
