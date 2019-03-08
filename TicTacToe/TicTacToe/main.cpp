#include "TicTacToe.h"
#include <QtWidgets/QApplication>
void testD() {
	Joc j(1, "xo-xo-xo", "x", "neinceput");
	assert(j.getId() == 1);
	assert(j.getNext() == "x");
	assert(j.getStare() == "neinceput");
	assert(j.getTabla() == "xo-xo-xo");
	j.setId(2);
	j.setNext('o');
	j.setStare("n");
	j.setTabla("xo-");
	assert(j.getId() == 2);
	assert(j.getNext() == "o");
	assert(j.getStare() == "n");
	assert(j.getTabla() == "xo-");
}

void testRepo() {
	Repository r;
	r.ReadFromFile();
	assert(r.dim() == 1);
	Joc j(2, "xo-xo-xo", "x", "neinceput");
	Joc ju(3, "xo-xo-xo", "x", "inceput");
	r.addToRepo(j);
	assert(r.dim() == 2);
	
	r.update(2, ju);
	assert(r.getEl(3).getStare() == "inceput");

}

void testService() {
	Service s;
	assert(s.getRepo().dim() == 0);
	s.adaugaJoc("xo-xo-xo-", "o", "neinceput");
	assert(s.getRepo().dim() == 1);
}
void test() {
	testD();
	testRepo();
	//testService();
}
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	Programatori w;
	nn n;
	test();
	w.setMinimumHeight(500);
	w.setMinimumWidth(1000);
	w.show();
	//n.show();
	return a.exec();
}
