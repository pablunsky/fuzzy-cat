#ifndef CONFIG_H
#define CONFIG_H
#include "listarutas.h"
#include "transbordo.h"
#include <QMainWindow>

namespace Ui {
class Config;
}

class Config : public QMainWindow
{
    Q_OBJECT

public:
    explicit Config(QWidget *parent = 0);
    ~Config();

private slots:
    void on_pushButton_clicked();

private:
    Ui::Config *ui;
    bool exist(QString codEstacion);
    ListaRutas *rutas;
    ListaRutas* getRutas(QString codigoEstacion);
    const QString BASE = "http://localhost:8080/servidor-1.0-SNAPSHOT/webresources";

    Transbordo *infoTransbordo;
};

#endif // CONFIG_H
