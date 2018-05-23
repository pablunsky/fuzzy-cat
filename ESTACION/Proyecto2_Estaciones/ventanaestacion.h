#ifndef VENTANAESTACION_H
#define VENTANAESTACION_H
#include "listarutas.h"
#include <QWidget>
#include "transbordo.h"
#include <QListWidgetItem>

namespace Ui {
class VentanaEstacion;
}

class VentanaEstacion : public QWidget
{
    Q_OBJECT

public:
    explicit VentanaEstacion(Transbordo *infoTransbordo, ListaRutas *rutas, QWidget *parent = 0);
    ~VentanaEstacion();

private slots:

    void on_pushButton_back_clicked();
    void verificarRuta();
    void on_listaWidget_itemDoubleClicked(QListWidgetItem *item);

private:

    Ui::VentanaEstacion *ui;
    void agregarRutas(ListaRutas *rutas);
    Transbordo *infoTransbordo;
    ListaRutas *rutas;
    const QString BASE = "http://localhost:8080/UrbanEDD/webresources";


};

#endif // VENTANAESTACION_H
