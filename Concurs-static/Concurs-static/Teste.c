#include "Teste.h"
#include "Persoana.h"
#include "Repo.h"
#include "Validare.h"
#include "Service.h"


void test()
{
	testCreeaza();
	testGetId();
	testGetNume();
	testGetPrenume();
	testGetScor();
	testSetNume();
	testSetPrenume();
	testSetScor();
	testEqual();
	test_repo();
	testValidator();
	testService();
	testFiltrareScorMic();
	testFiltrareLitera();
	testSortareNume();
	testSortareScor();
}

/*
Testeaza crearea unei persoane noi
*/
void testCreeaza()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	assert(p.id == 1);
	assert(p.scor == 100);
	assert(strcmp(p.nume, "Ion") == 0);
	assert(strcmp(p.prenume, "Dan") == 0);
}

/*
Testeaza functia getId
*/
void testGetId()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	int id = getId(p);
	assert(id == 1);
}

/*
Testeaza functia getNume
*/
void testGetNume()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	char nume[20];
	getNume(p, nume);
	assert(strcmp(nume, "Ion") == 0);
}

/*
Testeaza functia getPrenume
*/
void testGetPrenume()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	char prenume[20];
	getPrenume(p, prenume);
	assert(strcmp(prenume, "Dan") == 0);
}

/*
Testeaza functia getScor
*/
void testGetScor()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	int scor = getScor(p);
	assert(scor == 100);
}

/*
Testeaza functia setNume
*/
void testSetNume()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	setNume(&p, "Ionela");
	assert(strcmp(p.nume, "Ionela") == 0);
}

/*
Testeaza functia setPrenume
*/
void testSetPrenume()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	setPrenume(&p, "Daniela");
	assert(strcmp(p.prenume, "Daniela") == 0);
}

/*
Testeaza functia SetScor
*/
void testSetScor()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	setScor(&p, 80);
	assert(p.scor == 80);
}

void testEqual()
{
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	Persoana p1 = creeaza(1, "Ion", "Dan", 100);
	assert(equalPersoana(p, p1));
	Persoana p2 = creeaza(1, "Ioan", "Dan", 100);
	assert(!equalPersoana(p2, p1));
}


void test_repo()
{
	ListaPersoane list = makeList();
	assert(size(&list) == 0);
	Persoana p = creeaza(1, "Ion", "Dan", 100);
	Persoana p1 = creeaza(2, "Ionut", "Danut", 10);
	adauga(&list, p);
	assert(adauga(&list, p1) == SUCCES);
	assert(adauga(&list, p1) == PERSOANA_EXISTA_DEJA);
	assert(equalPersoana(getPersoana(&list, 0), p));
	assert(equalPersoana(getPersoana(&list, 1), p1));
	assert(find(&list, p) == 0);
	assert(size(&list) == 2);
	assert(sterge(&list,p) == SUCCES);
	assert(sterge(&list, p) == PERSOANA_NU_EXISTA);
	assert(find(&list, p) == PERSOANA_NU_EXISTA);
	assert(modificaPersoana(&list, p, p1) == PERSOANA_NU_EXISTA);
	assert(modificaPersoana(&list, p1, p) == SUCCES);
	assert(setPersoana(&list, 3, p) == PERSOANA_NU_EXISTA);
	assert(setPersoana(&list, 0, p1) == SUCCES);
}

void testValidator()
{
	Persoana p = creeaza(1, "Ion", "Dan", -100);
	assert(validare(p) == SCOR_INVALID);
	Persoana p1 = creeaza(2, "", "Danut", 10);
	assert(validare(p1) == NUME_VID);
	Persoana p2 = creeaza(2, "A", "", 10);
	assert(validare(p2) == PRENUME_VID);
	Persoana p3 = creeaza(2, "Ion", "Danut", 10);
	assert(validare(p3) == SUCCES);
}

void testService()
{
	ListaPersoane list = makeList();
	Service serv = creeareService(&list);
	assert(adaugare(&serv, 1, "Ion", "Maria", 100) == SUCCES);
	assert(adaugare(&serv, 2, "Iossn", "Mariaaa", 10) == SUCCES);
	assert(getdim(&serv) == 2);
	assert(adaugare(&serv, 1, "Ion", "Maria", 100) == PERSOANA_EXISTA_DEJA);
	assert(adaugare(&serv, 1, "Ion", "Maria", -1) == SCOR_INVALID);
	assert(adaugare(&serv, 1, "", "Maria", 100) == NUME_VID);
	assert(adaugare(&serv, 1, "A", "", 100) == PRENUME_VID);
	assert(getLista(&serv) == &list);
	assert(actualizare(&serv,1, "Ion", "Maria", 100, "Alina", "Faluta",50) == SUCCES);
	assert(actualizare(&serv, 1, "Ion", "Maria", 100, "Alina1", "Faluta1", 50) == PERSOANA_NU_EXISTA);
	assert(stergere(&serv, 1, "Alina", "Faluta",50) == SUCCES);
	assert(stergere(&serv, 1, "Ion", "Maria", 100) == PERSOANA_NU_EXISTA);
	assert(adaugare(&serv, 1, "Iona", "Ana", 100) == SUCCES);
	assert(adaugare(&serv, 1, "Ana", "Ana", 90) == SUCCES);

}

void testFiltrareScorMic()
{
	
	ListaPersoane l = makeList();
	Service serv = creeareService(&l);
	ListaPersoane filt;
	adaugare(&serv, 1, "Ion", "Dan", 100);
	adaugare(&serv, 2, "Ionut", "Danut", 10);
	adaugare(&serv, 3, "Ana", "Blandiana", 100);
	adaugare(&serv, 4, "Gabi", "Mircea", 10);
	adaugare(&serv, 5, "jmk", "buf", 100);
	adaugare(&serv, 6, "Sfagrig", "Danut", 10);
	assert(getdim(&serv)==6);
	filt = filtrareScorMic(&serv, 20);
	assert(filt.nr_pers == 3);
}

void testFiltrareLitera()
{
	ListaPersoane l = makeList();
	Service serv = creeareService(&l);
	ListaPersoane filt;
	adaugare(&serv, 1, "Ion", "Dan", 100);
	adaugare(&serv, 2, "Ionut", "Danut", 10);
	adaugare(&serv, 3, "Ana", "Blandiana", 100);
	adaugare(&serv, 4, "Gabi", "Mircea", 10);
	adaugare(&serv, 5, "jmk", "buf", 100);
	adaugare(&serv, 6, "Sfagrig", "Danut", 10);
	assert(getdim(&serv) == 6);
	filt = filtrareLitera(&serv, 'A');
	assert(filt.nr_pers == 1);
}

void testSortareNume()
{
	ListaPersoane l = makeList();
	Service serv = creeareService(&l);
	ListaPersoane *filt;
	adaugare(&serv, 1, "Ion", "Dan", 100);
	adaugare(&serv, 2, "Ionut", "Danut", 10);
	adaugare(&serv, 3, "Ana", "Blandiana", 100);
	adaugare(&serv, 4, "Gabi", "Mircea", 10);
	adaugare(&serv, 5, "jmk", "buf", 100);
	adaugare(&serv, 6, "Sfagrig", "Danut", 10);
	assert(getdim(&serv) == 6);
	sortareNume(&serv, 1);
	filt = getLista(&serv);
	assert(strcmp(filt->lista[0].nume, "Ana") == 0);
	sortareNume(&serv, -1);
	filt = getLista(&serv);
	assert(strcmp(filt->lista[0].nume, "jmk") == 0);
}

void testSortareScor()
{
	ListaPersoane l = makeList();
	Service serv = creeareService(&l);
	ListaPersoane *filt;
	adaugare(&serv, 1, "Ion", "Dan", 100);
	adaugare(&serv, 2, "Ionut", "Danut", 10);
	adaugare(&serv, 3, "Ana", "Blandiana", 100);
	adaugare(&serv, 4, "Gabi", "Mircea", 1);
	adaugare(&serv, 5, "jmk", "buf", 100);
	adaugare(&serv, 6, "Sfagrig", "Danut", 10);
	assert(getdim(&serv) == 6);
	sortareScor(&serv, 1);
	filt = getLista(&serv);
	assert(filt->lista[0].scor == 1);
	sortareScor(&serv, -1);
	filt = getLista(&serv);
	assert(filt->lista[0].scor == 100);
}