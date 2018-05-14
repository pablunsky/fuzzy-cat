#ifndef CLIENTETICKETS_H
#define CLIENTETICKETS_H

#include <QMainWindow>

namespace Ui {
class ClienteTickets;
}

class ClienteTickets : public QMainWindow
{
    Q_OBJECT

public:
    explicit ClienteTickets(QWidget *parent = 0);
    ~ClienteTickets();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_4_clicked();

    void on_pushButton_6_clicked();

    void on_pushButton_8_clicked();

    void on_pushButton_7_clicked();

    void on_pushButton_9_clicked();

    void on_pushButton_3_clicked();

    void on_pushButton_10_clicked();

    void on_pushButton_11_clicked();

private:
    double valor;
    const QString BASE = "http://localhost:8080/servidor-1.0-SNAPSHOT/webresources";
    Ui::ClienteTickets *ui;
};

#endif // CLIENTETICKETS_H
