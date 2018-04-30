/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author ciberveliz
 */
public class NodoGrafo {
    private String nombre;
    private String codigo;
    private Double precio;
    private Double latitud;
    private Double longitud;
    ListaAristas aristas;
    
    public NodoGrafo(String nombre,String codigo,Double precio,Double latitud,Double longitud)
    {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.latitud = latitud;
        this.longitud = longitud;
        aristas = new ListaAristas();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    public Boolean aristaRepetida(NodoGrafo destino)
    {
        NodoArista aux = this.aristas.primero;
        while(aux != null && aux.arista.getDestino() != destino)
        {
            aux = aux.sig;
        }
        return aux != null;
    }
}
