/********************************************************************************
** Form generated from reading UI file 'clientetickets.ui'
**
** Created by: Qt User Interface Compiler version 5.9.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CLIENTETICKETS_H
#define UI_CLIENTETICKETS_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStackedWidget>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ClienteTickets
{
public:
    QWidget *centralWidget;
    QStackedWidget *stackedWidget;
    QWidget *page;
    QPushButton *pushButton;
    QPushButton *pushButton_2;
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QPushButton *pushButton_5;
    QWidget *page_2;
    QPushButton *pushButton_3;
    QPushButton *pushButton_4;
    QWidget *page_3;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *ClienteTickets)
    {
        if (ClienteTickets->objectName().isEmpty())
            ClienteTickets->setObjectName(QStringLiteral("ClienteTickets"));
        ClienteTickets->resize(735, 457);
        centralWidget = new QWidget(ClienteTickets);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        stackedWidget = new QStackedWidget(centralWidget);
        stackedWidget->setObjectName(QStringLiteral("stackedWidget"));
        stackedWidget->setGeometry(QRect(0, 0, 731, 411));
        page = new QWidget();
        page->setObjectName(QStringLiteral("page"));
        pushButton = new QPushButton(page);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(500, 40, 201, 251));
        pushButton_2 = new QPushButton(page);
        pushButton_2->setObjectName(QStringLiteral("pushButton_2"));
        pushButton_2->setGeometry(QRect(500, 310, 201, 71));
        label = new QLabel(page);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(30, 40, 401, 101));
        QFont font;
        font.setFamily(QStringLiteral("Julius Sans One"));
        font.setPointSize(53);
        label->setFont(font);
        label_2 = new QLabel(page);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(40, 130, 371, 51));
        QFont font1;
        font1.setFamily(QStringLiteral("Michroma"));
        font1.setPointSize(24);
        font1.setBold(false);
        font1.setWeight(50);
        label_2->setFont(font1);
        label_3 = new QLabel(page);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(20, 200, 411, 111));
        QFont font2;
        font2.setFamily(QStringLiteral("Nimbus Sans L"));
        font2.setPointSize(14);
        label_3->setFont(font2);
        pushButton_5 = new QPushButton(page);
        pushButton_5->setObjectName(QStringLiteral("pushButton_5"));
        pushButton_5->setGeometry(QRect(170, 330, 131, 51));
        stackedWidget->addWidget(page);
        page_2 = new QWidget();
        page_2->setObjectName(QStringLiteral("page_2"));
        pushButton_3 = new QPushButton(page_2);
        pushButton_3->setObjectName(QStringLiteral("pushButton_3"));
        pushButton_3->setGeometry(QRect(500, 280, 211, 91));
        pushButton_4 = new QPushButton(page_2);
        pushButton_4->setObjectName(QStringLiteral("pushButton_4"));
        pushButton_4->setGeometry(QRect(20, 340, 91, 31));
        stackedWidget->addWidget(page_2);
        page_3 = new QWidget();
        page_3->setObjectName(QStringLiteral("page_3"));
        stackedWidget->addWidget(page_3);
        ClienteTickets->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(ClienteTickets);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 735, 27));
        ClienteTickets->setMenuBar(menuBar);
        mainToolBar = new QToolBar(ClienteTickets);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        ClienteTickets->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(ClienteTickets);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        ClienteTickets->setStatusBar(statusBar);

        retranslateUi(ClienteTickets);

        stackedWidget->setCurrentIndex(0);


        QMetaObject::connectSlotsByName(ClienteTickets);
    } // setupUi

    void retranslateUi(QMainWindow *ClienteTickets)
    {
        ClienteTickets->setWindowTitle(QApplication::translate("ClienteTickets", "ClienteTickets", Q_NULLPTR));
        pushButton->setText(QApplication::translate("ClienteTickets", "COMPRAR", Q_NULLPTR));
        pushButton_2->setText(QApplication::translate("ClienteTickets", "REEMBOLSO", Q_NULLPTR));
        label->setText(QApplication::translate("ClienteTickets", "UrbanEDD", Q_NULLPTR));
        label_2->setText(QApplication::translate("ClienteTickets", "Viaja al futuro, hoy", Q_NULLPTR));
        label_3->setText(QApplication::translate("ClienteTickets", " Unete al viaje de tu vida, junto a \n"
" miles de personas que disfrutan ya \n"
" de la comodidad, seguridad y atencion \n"
" personalizada que solo brinda UrbanEDD", Q_NULLPTR));
        pushButton_5->setText(QApplication::translate("ClienteTickets", "> Descubrir", Q_NULLPTR));
        pushButton_3->setText(QApplication::translate("ClienteTickets", "\302\241Comprar!", Q_NULLPTR));
        pushButton_4->setText(QApplication::translate("ClienteTickets", "Cancelar", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class ClienteTickets: public Ui_ClienteTickets {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CLIENTETICKETS_H
