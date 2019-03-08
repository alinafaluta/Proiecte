#pragma once
#include "Repo.h"
#include <exception>
using namespace std;
class Observer {
public:
	virtual void update() = 0;
};

class Observable {
private:
	std::vector<Observer*> observers;
public:
	void addObserver(Observer* obs) {
		observers.push_back(obs);
	}
	void notifyObserver() {
		for (auto i : observers)
			i->update();
	}
};


class Service :public Observable
{
private:
	Repository r;
public:
	Service() =default;
	/*adauga un joc in repository
	ii seteaza id-ul automat*/
	void adaugaJoc(string tab, string next, string stare);
	/*returneaza repository-ul*/
	Repository getRepo() {
		return r;
	}
	vector<Joc> alfabetic();
	/*modifica un element din repo dupa id  si arunga erori daca datele nu indeplinesc conditiile*/
	void update(int id, string tab, string next, string stare);
	~Service()=default;
};


