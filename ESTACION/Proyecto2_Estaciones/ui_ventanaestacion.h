/********************************************************************************
** Form generated from reading UI file 'ventanaestacion.ui'
**
** Created by: Qt User Interface Compiler version 5.9.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_VENTANAESTACION_H
#define UI_VENTANAESTACION_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VentanaEstacion
{
public:
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QLineEdit *lineEdit_ticket;
    QLabel *label_correct;
    QLabel *label_incorrect;
    QLabel *label_error;
    QPushButton *pushButton_back;
    QListWidget *listaWidget;

    void setupUi(QWidget *VentanaEstacion)
    {
        if (VentanaEstacion->objectName().isEmpty())
            VentanaEstacion->setObjectName(QStringLiteral("VentanaEstacion"));
        VentanaEstacion->resize(400, 344);
        layoutWidget = new QWidget(VentanaEstacion);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(20, 10, 351, 61));
        verticalLayout = new QVBoxLayout(layoutWidget);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(layoutWidget);
        label->setObjectName(QStringLiteral("label"));

        verticalLayout->addWidget(label);

        lineEdit_ticket = new QLineEdit(layoutWidget);
        lineEdit_ticket->setObjectName(QStringLiteral("lineEdit_ticket"));

        verticalLayout->addWidget(lineEdit_ticket);

        label_correct = new QLabel(VentanaEstacion);
        label_correct->setObjectName(QStringLiteral("label_correct"));
        label_correct->setEnabled(true);
        label_correct->setGeometry(QRect(90, 270, 50, 50));
        label_incorrect = new QLabel(VentanaEstacion);
        label_incorrect->setObjectName(QStringLiteral("label_incorrect"));
        label_incorrect->setGeometry(QRect(240, 270, 50, 50));
        label_error = new QLabel(VentanaEstacion);
        label_error->setObjectName(QStringLiteral("label_error"));
        label_error->setGeometry(QRect(20, 80, 351, 41));
        label_error->setAlignment(Qt::AlignCenter);
        pushButton_back = new QPushButton(VentanaEstacion);
        pushButton_back->setObjectName(QStringLiteral("pushButton_back"));
        pushButton_back->setGeometry(QRect(10, 300, 30, 30));
        listaWidget = new QListWidget(VentanaEstacion);
        listaWidget->setObjectName(QStringLiteral("listaWidget"));
        listaWidget->setGeometry(QRect(20, 130, 351, 121));

        retranslateUi(VentanaEstacion);

        QMetaObject::connectSlotsByName(VentanaEstacion);
    } // setupUi

    void retranslateUi(QWidget *VentanaEstacion)
    {
        VentanaEstacion->setWindowTitle(QApplication::translate("VentanaEstacion", "Form", Q_NULLPTR));
        label->setText(QApplication::translate("VentanaEstacion", "Ingrese el c\303\263digo de su ticket:", Q_NULLPTR));
        label_correct->setText(QString());
        label_incorrect->setText(QString());
        label_error->setText(QString());
        pushButton_back->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class VentanaEstacion: public Ui_VentanaEstacion {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VENTANAESTACION_H
