/********************************************************************************
** Form generated from reading UI file 'TicTacToe.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TICTACTOE_H
#define UI_TICTACTOE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_TicTacToeClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *TicTacToeClass)
    {
        if (TicTacToeClass->objectName().isEmpty())
            TicTacToeClass->setObjectName(QStringLiteral("TicTacToeClass"));
        TicTacToeClass->resize(600, 400);
        menuBar = new QMenuBar(TicTacToeClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        TicTacToeClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(TicTacToeClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        TicTacToeClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(TicTacToeClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        TicTacToeClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(TicTacToeClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        TicTacToeClass->setStatusBar(statusBar);

        retranslateUi(TicTacToeClass);

        QMetaObject::connectSlotsByName(TicTacToeClass);
    } // setupUi

    void retranslateUi(QMainWindow *TicTacToeClass)
    {
        TicTacToeClass->setWindowTitle(QApplication::translate("TicTacToeClass", "TicTacToe", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TicTacToeClass: public Ui_TicTacToeClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TICTACTOE_H
