#ifndef LISTA_H
#define LISTA_H


//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a structurii Nod;
struct Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod *PNod;

typedef struct Nod {
	TElem e;
	PNod urm;
};

typedef struct {
	//prim este adresa primului Nod din lista
	PNod _prim;
} Lista;

//operatii pe lista - INTERFATA

//crearea unei liste din valori citite pana la 0
Lista creare();
//tiparirea elementelor unei liste
void tipar(Lista l);
// destructorul listei
void distrug(Lista l);

//verifica daca lista este vida
bool eListaVida(Lista l);

//creeaza o lista vida
Lista creeazaListaVida();

// returneaza primul element din lista
int primElem(Lista l);

//returneaza lista fara primul element
Lista sublista(Lista l);

// adauga elem la inceputul listei l
Lista adaugaInceput(int elem, Lista l);

bool eMultime(Lista l);

bool apare(int e, Lista l);

int distincte(Lista l);
void verifMultime(Lista l);
void verifDistincte(Lista l);
#endif