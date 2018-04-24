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

private:
    Ui::ClienteTickets *ui;
};

#endif // CLIENTETICKETS_H
