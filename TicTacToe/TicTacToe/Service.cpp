#include "Service.h"

void Service::adaugaJoc(string tab, string next, string stare)
{
	
	int id = r.dim();
	id++;
	Joc j{ id,tab,next,stare };
	r.addToRepo(j);
	notifyObserver();
}

vector<Joc> Service::alfabetic()
{
	vector<Joc> ve = r.getElemente();
	for(int i=0;i<ve.size();i++)
		for(int j=0;j<ve.size();j++)
			if (ve[i].getStare() > ve[j].getStare())
			{
				Joc aux = ve[i];
				ve[i] = ve[j];
				ve[j] = aux;
			}
	return ve;
}

void Service::update(int id, string tab, string next, string stare)
{
	if (next.size() > 1 || next.size()<1)
		throw(std::exception("trebuie doar un caracter pt next"));
	if (next != "o" && next != "x")
		throw(std::exception("jucatorul urmator poate fi doar x sau o"));
	if (tab.size()>9 || tab.size()<9)
		throw(std::exception("tabla are 9 caractere"));
	for(int i=0;i<9;i++)
		if (tab[i] != 'x' &&tab[i] != 'o'&&tab[i] != '-')
			throw(std::exception("tabla poate contine doar x, o sau -"));
	if (stare != "in derulare" && stare != "terminat" && stare != "neinceput")
		throw(std::exception("Jocul poate avea doar 3 stari : neinceput,terminat,in derulare"));
	Joc j{ id,tab,next,stare };
	r.update(id, j);
}
