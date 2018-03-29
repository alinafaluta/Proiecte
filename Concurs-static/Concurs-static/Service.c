#include "Persoana.h"
#include <stdlib.h>
#include <string.h>
#include "Repo.h"
#include <ctype.h>
#include <stdio.h>
#include "Service.h"
#include "Validare.h"
Service creeareService(ListaPersoane * lista)
{
	Service service;
	service.lista = lista;
	return service;
}

ListaPersoane* getLista(Service* service)
{
	return service->lista;
}
int adaugare(Service* service, int id, char nume[20], char prenume[20], int scor)
{
	Persoana p = creeaza(id, nume, prenume, scor);
	int val = validare(p);
	if (val) return val;
	val = adauga(service->lista, p);
	if (val) return val;
	return SUCCES;
}
int stergere(Service* service,int id, char nume[20], char prenume[20],int scor)
{
	Persoana p = creeaza(id, nume, prenume, scor);
	int val = validare(p);
	if (val) return val;
	val = sterge(service->lista, p);
	if (val) return val;
	return SUCCES;
}

int actualizare(Service* service,int id, char nume[20], char prenume[20], int scor, char nume_nou[20], char prenume_nou[20], int scor_nou)
{
	Persoana p_n = creeaza(id, nume_nou, prenume_nou, scor_nou);
	Persoana p_v = creeaza(id, nume, prenume, scor);
	int val = validare(p_v);
	if (val) return val;
	val = validare(p_n);
	if (val) return val;
	val = modificaPersoana(service->lista, p_v,p_n);
	if (val) return val;
	return SUCCES;
}
ListaPersoane filtrareScorMic(Service* service, int val)
{
	ListaPersoane filtrare = makeList();
	for (int i = 0; i < service->lista->nr_pers; i++)
	{
		if (service->lista->lista[i].scor < val)
		{
			Persoana p = service->lista->lista[i];
			adauga(&filtrare, p);
		}
	}
	return filtrare;
}

ListaPersoane filtrareLitera(Service* service, char a)
{
	ListaPersoane filtrare = makeList();
	for (int i = 0; i < service->lista->nr_pers; i++)
	{
		if (service->lista->lista[i].nume[0] == a)
		{
			Persoana p = service->lista->lista[i];
			adauga(&filtrare, p);
		}
	}
	return filtrare;
}

void sortareNume(Service* service, int val)
{
	Persoana a;
	if (val == 1)
	{
		for (int i = 0; i < service->lista->nr_pers - 1; i++)
		{
			for (int j = i + 1; j <service->lista->nr_pers; j++)
			{
				if (strcmp(service->lista->lista[i].nume, service->lista->lista[j].nume) > 0)
				{
					a = service->lista->lista[i];
					service->lista->lista[i] = service->lista->lista[j];
					service->lista->lista[j] = a;
				}
			}
		}
	}
	else
		if (val == -1)
		{
			for (int i = 0; i < service->lista->nr_pers - 1; i++)
			{
				for (int j = i + 1; j <service->lista->nr_pers; j++)
				{
					if (strcmp(service->lista->lista[i].nume, service->lista->lista[j].nume) < 0)
					{
						a = service->lista->lista[i];
						service->lista->lista[i] = service->lista->lista[j];
						service->lista->lista[j] = a;
					}
				}
			}
		}
}
void sortareScor(Service* service, int val)
{
	Persoana a;
	if (val == 1)
	{
		for (int i = 0; i < service->lista->nr_pers - 1; i++)
		{
			for (int j = i + 1; j <service->lista->nr_pers; j++)
			{
				if (service->lista->lista[i].scor>service->lista->lista[j].scor)
				{
					a = service->lista->lista[i];
					service->lista->lista[i] = service->lista->lista[j];
					service->lista->lista[j] = a;
				}
			}
		}
	}
	if (val == -1)
	{
		for (int i = 0; i < service->lista->nr_pers - 1; i++)
		{
			for (int j = i + 1; j <service->lista->nr_pers; j++)
			{
				if (service->lista->lista[i].scor<service->lista->lista[j].scor)
				{
					a = service->lista->lista[i];
					service->lista->lista[i] = service->lista->lista[j];
					service->lista->lista[j] = a;
				}
			}
		}
	}
}

int getdim(Service* s)
{
	return s->lista->nr_pers;
}