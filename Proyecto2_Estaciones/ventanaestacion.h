#ifndef VENTANAESTACION_H
#define VENTANAESTACION_H
#include "listarutas.h"
#include <QWidget>

namespace Ui {
class VentanaEstacion;
}

class VentanaEstacion : public QWidget
{
    Q_OBJECT

public:
    explicit VentanaEstacion(QString codigo,ListaRutas *rutas, QWidget *parent = 0);
    ~VentanaEstacion();

private slots:

    void on_pushButton_back_clicked();
    void verificarRuta();
private:
    Ui::VentanaEstacion *ui;
    void agregarRutas(ListaRutas *rutas);

};

#endif // VENTANAESTACION_H
