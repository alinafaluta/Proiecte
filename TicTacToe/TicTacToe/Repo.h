#pragma once
#include <vector>
#include <iostream>
#include <fstream>
#include <string>
#include <assert.h>
#include "Domain.h"
using namespace std;
class Repository
{
private:
	std::vector<Joc> repo;
	string fileName;
public:
	Repository() :fileName{ "fis.txt" } {};
	Repository(string file) :fileName{ file } {};
	/*citeste din fisier*/
	void ReadFromFile() {
		ifstream f(fileName);
		while (!f.eof())
		{
			int x;
			string tabla;
			string next;
			string stare;
			f >> x >> tabla >> next >> stare;
			Joc j{ x,tabla,next,stare };
			repo.push_back(j);
		}
		f.close();
	};
	/*adauga in repository si arunca eroare daca id-ul nu este unic*/
	void addToRepo(Joc ob) {
		for (int i = 0; i < repo.size(); i++)
			if (ob.getId() == repo[i].getId())
				throw("Element existent");
		repo.push_back(ob);
		//WriteToFile();
	};
	/*inlocuieste un element din repository cu altul nou*/
	void update(int id, Joc ob2) {
		for (int i = 0; i < repo.size(); i++)
			if (id == repo[i].getId())
				repo[i] = ob2;
		//WriteToFile();
	};
	/*
	returneaza dimensiunea repo*/
	int dim() {
		return repo.size();
	}
	/*returneaza un element din repo dupa id
	daca acesta nu este gasit returneaza un element predefinit*/
	Joc getEl(int id) {
		for (int i = 0; i < repo.size(); i++)
			if (id == repo[i].getId())
				return repo[i];
		return Joc{ 0,"a","b","c" };
	}
	/*returneaza elementele din repo*/
	vector<Joc> getElemente()
	{
		return repo;
	}
	~Repository() = default;
};
