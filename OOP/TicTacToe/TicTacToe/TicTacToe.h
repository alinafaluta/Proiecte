#pragma once

#include <QtWidgets/QMainWindow>

#include <QtWidgets/QMainWindow>
#include <QTableWidget>
#include <qboxlayout.h>
#include <qlistwidget.h>
#include <vector>
#include <qformlayout.h>
#include <qpushbutton.h>
#include <qlayout.h>
#include <qmessagebox.h>
#include <qlineedit.h>

#include <qlabel.h>
#include "Service.h"
using namespace std;
class TicTacToe : public QWidget, public Observer
{
public:
	TicTacToe();
	~TicTacToe() = default;
private slots:
	void reloadList(vector<Joc> vic) {
		int poz = 0;
		for (auto i : vic)
		{
			string id = to_string(i.getId());
			m_pTableWidget->setItem(poz, 0, new QTableWidgetItem(QString::fromStdString(id)));
			m_pTableWidget->setItem(poz, 1, new QTableWidgetItem(QString::fromStdString(i.getTabla())));
			m_pTableWidget->setItem(poz, 2, new QTableWidgetItem(QString::fromStdString(i.getNext())));
			m_pTableWidget->setItem(poz, 3, new QTableWidgetItem(QString::fromStdString(i.getStare())));
			poz++;
		}
	};
	void update()override {
		reloadList(s.alfabetic());
	}
private:
	Repository r;
	Service s;
	QListWidget* listuta;
	QTableWidget * m_pTableWidget;
	QStringList m_TableHeader;
	QHBoxLayout* cutiuta;

};
class nn :public QWidget, public Observable{
public:
	nn() {
		
		verti = new QVBoxLayout;
		setLayout(verti);
		add = new QPushButton("Adauga");
		

		verti->addWidget(add);
		formular = new QFormLayout;

		id = new QLineEdit;
		stare = new QLineEdit;
		tabla = new QLineEdit;
		next = new QLineEdit;
		t = new TicTacToe;
		addObserver(t);
		id = new QLineEdit;
		formular->addRow(new QLabel("Id: "), id);
		formular->addRow(new QLabel("Tabla: "), tabla);
		formular->addRow(new QLabel("Stare: "), stare);
		formular->addRow(new QLabel("next: "), next);
		QObject::connect(add, &QPushButton::clicked, [this]() {adaug(); });
		verti->addLayout(formular);
	}
private slots:
	void adaug() {
		string descr = id->text().toStdString();
		string st = stare->text().toStdString();
		string p1 =tabla->text().toStdString();
		string p2 = next->text().toStdString();
		try {

			s.adaugaJoc(p1, p2, st);
		}
		catch (std::exception& e) {
			QMessageBox::information(this, "",e.what());
		}
	}
private:
	TicTacToe * t;
	Service s;
	QVBoxLayout * verti;
	QPushButton* add;
	QLineEdit* tabla;
	QLineEdit* next;
	QLineEdit* id;
	QLineEdit* stare;
	QFormLayout* formularAdauga;
	QFormLayout* formular;
};

class Programatori : public QWidget, public Observer
{

public:
	Programatori() {
		s.adaugaJoc(" x-oxo-xoo", "x", "terminat");
		s.adaugaJoc("xo-xoo-oo", "o", "inceput");
		s.adaugaJoc(" x-oxo-xoo", "x", "terminat");
		cutiuta = new QHBoxLayout;
		setLayout(cutiuta);
		m_pTableWidget = new QTableWidget(this);
		m_pTableWidget->setRowCount(20);
		m_pTableWidget->setColumnCount(4);
		m_TableHeader << "ID" << "Tabela" << "Next" << "Stare";
		m_pTableWidget->setHorizontalHeaderLabels(m_TableHeader);
		//m_pTableWidget->verticalHeader()->setVisible(true);
		m_pTableWidget->setEditTriggers(QAbstractItemView::NoEditTriggers);
		m_pTableWidget->setSelectionBehavior(QAbstractItemView::SelectRows);
		m_pTableWidget->setSelectionMode(QAbstractItemView::SingleSelection);
		m_pTableWidget->setShowGrid(true);
		m_pTableWidget->setStyleSheet("QTableView {selection-background-color: red;}");
		//m_pTableWidget->setGeometry(QApplication::desktop()->screenGeometry());
		m_pTableWidget->setGeometry(200, 200, 200, 200);
		//insert data
		//m_pTableWidget->setItem(0, 1, new QTableWidgetItem("Hello"));
		//m_pTableWidget->setItem(4, 3, new QTableWidgetItem("aici e ceva"));
		//cutiuta->addStretch(1000);
		cutiuta->addWidget(m_pTableWidget);
		QObject::connect(m_pTableWidget, SIGNAL(cellDoubleClicked(int, int)),
			this, SLOT(cellSelected(int, int)));
		verti = new QVBoxLayout;
		adauga = new QPushButton("Adauga");
		formularFilt = new QFormLayout;
		
		filtreaza = new QPushButton("modifica");

		verti->addLayout(formularFilt);

		verti->addWidget(adauga);
		formular = new QFormLayout;
		formularFilt=new QFormLayout;
		idu = new QLineEdit;
		starea = new QLineEdit;
		tabla = new QLineEdit;
		next = new QLineEdit;
		formularFilt->addRow(new QLabel("id "), idu);
		formularFilt->addRow(new QLabel("tabela "), tabla);
		formularFilt->addRow(new QLabel("next "), next);
		formularFilt->addRow(new QLabel("stare "), starea);
		descriere = new QLineEdit;
		stare = new QLineEdit;
		programator1 = new QLineEdit;
		programator2 = new QLineEdit;
		programator3 = new QLineEdit;
		programator4 = new QLineEdit;
		id = new QLineEdit;
		//formular->addRow(new QLabel("Id: "), id);
		formular->addRow(new QLabel("Tabela "), descriere);
		formular->addRow(new QLabel("next: "), stare);
		//formular->addRow(new QLabel("stare: "), programator1);
		verti->addLayout(formular);
		verti->addWidget(filtreaza);
		verti->addLayout(formularFilt);
		QObject::connect(adauga, &QPushButton::clicked, [this]() { string descr = descriere->text().toStdString();
		string st = stare->text().toStdString();
		s.adaugaJoc(descr, st, "Neinceput");
		reloadList(s.getRepo().getElemente()); });

		QObject::connect(filtreaza, &QPushButton::clicked, [this]() {
			string i = idu->text().toStdString();
			int ide = stoi(i);
			string descr = starea->text().toStdString();
			string tab = tabla->text().toStdString();
			string n = next->text().toStdString();
			try {
				s.update(ide, tab, n, descr);
				reloadList(s.getRepo().getElemente());
				//notifyObserver();
			}
			catch (std::exception& e) {
				QMessageBox::information(this, "",
					e.what());
			}
	});
		cutiuta->addLayout(verti);
		reloadList(s.getRepo().getElemente());
	}
private slots:
	void refre();
	void adaug() {
		string i = id->text().toStdString();
		int ide = stoi(i);
		string descr = descriere->text().toStdString();
		string st = stare->text().toStdString();
		try {
			s.adaugaJoc(descr,st,"Neinceput");
			reloadList(s.getRepo().getElemente());
			//notifyObserver();
		}
		catch (std::exception& e) {
			QMessageBox::information(this, "",
				e.what());
		}
	}

	void reloadList(vector<Joc> vic) {
		int poz = 0;
		for (int i = 0; i<vic.size(); i++)
			for (int j = 0; j<vic.size(); j++)
				if (vic[i].getStare() > vic[j].getStare())
				{
					Joc aux = vic[i];
					vic[i] = vic[j];
					vic[j] = aux;
				}
		for (auto i : vic)
		{
			string id = to_string(i.getId());
			m_pTableWidget->setItem(poz, 0, new QTableWidgetItem(QString::fromStdString(id)));
			m_pTableWidget->setItem(poz, 1, new QTableWidgetItem(QString::fromStdString(i.getTabla())));
			m_pTableWidget->setItem(poz, 2, new QTableWidgetItem(QString::fromStdString(i.getNext())));
			m_pTableWidget->setItem(poz, 3, new QTableWidgetItem(QString::fromStdString(i.getStare())));
			poz++;
		}
	};
	void update() override {
		reloadList(s.getRepo().getElemente());
	}
private:
	Service s;
	QTableWidget * m_pTableWidget;
	QFormLayout* formular;
	QFormLayout* formularFilt;
	QPushButton* adauga;
	QPushButton* refresh;
	QPushButton* filtreaza;
	QLineEdit* filtrare;
	QLineEdit* id;
	QLineEdit* descriere;
	QLineEdit* stare;
	QLineEdit* programator1;
	QLineEdit* programator2;
	QLineEdit* programator3;
	QLineEdit* programator4;
	QStringList m_TableHeader;
	QHBoxLayout* cutiuta;
	QVBoxLayout* verti;
	QLineEdit* tabla;
	QLineEdit* next;
	QLineEdit* idu;
	QLineEdit* starea;
};
