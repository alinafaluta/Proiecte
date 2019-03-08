#pragma once
#include <string>
#include <assert.h>
using namespace std;
class Joc
{
private:
	int id;
	string tabla;
	string next;
	string stare;
public:
	/*constructor*/
	Joc(int i, string t, string n, string s) :id{ i }, tabla{ t }, next{ n }, stare{ s } {};
	/*returneaza id-ul jocului*/
	int getId() {
		return id;
	}
	/*returneaza tabla jocului (un string)*/
	string getTabla() {
		return tabla;
	}
	/*returneazza un string cu starea*/
	string getStare() {
		return stare;
	}
	/*
	 returneaza jucatorul urmator*/
	string getNext(){
		return next;
	}
	/*seteaza jucatorul urmator*/
	void setNext(char c) {
		next = c;
	}
	void setStare(string st) {
		stare = st;
	}
	void setTabla(string tab) {
		tabla = tab;
	}
	void setId(int i) {
		id = i;
	}
	~Joc()=default;
};


