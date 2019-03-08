#pragma once

#include"Persoana.h"
#include"Repo.h"

typedef struct Service {
	ListaPersoane* lista;

} Service;
Service creeareService(ListaPersoane* lista);

ListaPersoane* getLista(Service* service);
int adaugare(Service* service, int id, char nume[20], char prenume[20], int scor);
int stergere(Service* service, int id, char nume[20], char prenume[20], int scor);
int actualizare(Service* service, int id,char nume[20], char prenume[20], int scor,char nume_nou[20], char prenume_nou[20],int scor_nou);

ListaPersoane filtrareScorMic(Service* service, int val);
ListaPersoane filtrareLitera(Service* service, char a);
void sortareNume(Service* service, int val);
void sortareScor(Service* service, int val);

int getdim(Service* s);