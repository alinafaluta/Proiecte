#include "TicTacToe.h"
#include <qapplication.h>
#include <qmessagebox.h>
#include <qlineedit.h>
#include <QApplication>
#include <QDesktopWidget>
#include <QCoreApplication>
#include <QHeaderView>
#include <QMessageBox>
#include <qlabel.h>
#include <qpushbutton.h>
#include <string.h>
#include <string>

TicTacToe::TicTacToe()
{
	r = s.getRepo();

	s.adaugaJoc("xo-xo-xo-", "o", "neinceput");
	cutiuta = new QHBoxLayout;
	setLayout(cutiuta);
	//verti = new QVBoxLayout;
	setLayout(cutiuta);
	
	/*add = new QPushButton(this);
	add->setText("Adauga");
	tabla = new QLineEdit;
	next = new QLineEdit;
	formularAdauga = new QFormLayout(this);
	formularAdauga->addRow(tabla);
	formularAdauga->addRow(next);
	QObject::connect(add, &QPushButton::clicked, [this]() {adaug(); });
	cutiuta->addWidget(add);
	verti->addLayout(formularAdauga);
	cutiuta->addLayout(formularAdauga);
	s.adaugaJoc("xo-xo-xo-", "o", "inceput");
	reloadList(s.getRepo().getElemente());*/

	r = s.getRepo();
	cutiuta = new QHBoxLayout(this);
	setLayout(cutiuta);
	m_pTableWidget = new QTableWidget(this);
	m_pTableWidget->setRowCount(12);
	m_pTableWidget->setColumnCount(4);
	m_TableHeader << "ID" << "Tabela" << "Next" << "Stare";
	m_pTableWidget->setHorizontalHeaderLabels(m_TableHeader);
	m_pTableWidget->verticalHeader()->setVisible(true);
	m_pTableWidget->setEditTriggers(QAbstractItemView::NoEditTriggers);
	m_pTableWidget->setSelectionBehavior(QAbstractItemView::SelectRows);
	m_pTableWidget->setSelectionMode(QAbstractItemView::SingleSelection);
	m_pTableWidget->setShowGrid(true);
	m_pTableWidget->setStyleSheet("QTableView {selection-background-color: red;}");
	m_pTableWidget->setGeometry(QApplication::desktop()->screenGeometry());
	m_pTableWidget->setGeometry(10, 50, 600, 550);
	cutiuta->addWidget(m_pTableWidget);
	cutiuta->addWidget(m_pTableWidget);
	QObject::connect(m_pTableWidget, SIGNAL(cellDoubleClicked(int, int)),
		this, SLOT(cellSelected(int, int)));
	//n->show();
}



