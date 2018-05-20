#include "jsonreader.h"
#include <QJsonDocument>
#include <QJsonArray>
#include <QJsonObject>
#include <QMessageBox>
#include "ruta.h"

JsonReader::JsonReader()
{

}

void JsonReader::obtenerRutas(ListaRutas *rutas, QByteArray json)
{
    QJsonDocument doc = QJsonDocument::fromJson(json);
        if(! doc.isArray()){
            QMessageBox::warning(nullptr, "JSON Rutas", "Error en el archivo, el elemento raíz debe ser un arreglo");
            return;
        }
        QJsonArray array = doc.array();
        int max = array.size();

        for(int i = 0; i < max; i++) {
            QJsonValueRef ref = array[i];
            if(! ref.isObject())
                continue;
            QJsonObject obj = ref.toObject();

            QJsonValue codigo = obj["codigo"];
            if(codigo.isUndefined() || codigo.isNull())
                continue;
            QJsonValue nombre = obj["nombre"];
            if(nombre.isUndefined() || nombre.isNull())
                continue;
            QJsonValue color = obj["color"];
            if(color.isUndefined() || color.isNull())
                continue;

            QJsonValue precio = obj["precio"];
            if(precio.isUndefined() || precio.isNull())
                continue;

            Ruta *rutaTemp = new Ruta(codigo.toInt(),nombre.toString(),color.toString(),precio.toDouble());
            rutas->insertarRuta(rutaTemp);
        }
}
