/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author pablunsky
 */
public class NodoArbolB {
    
    private NodoArbolB[] hijos;
    private Ticket[] contenidos;
    private int llenos;
    public boolean eshoja;
    private int t;
    
    public NodoArbolB(int orden){
        t = orden;
        contenidos = new Ticket[2*orden-1];
        hijos = new NodoArbolB[orden*2];
        llenos = 0;
        eshoja = true;
    }
    
    public void insertarTicket(Ticket nuevo){
        int i = llenos - 1;
        if(eshoja){
            while((i>=0)&&(nuevo.getCodigo()<contenidos[i].getCodigo())){
                contenidos[i+1] = contenidos[i];
                i--;
            }
            llenos++;
            contenidos[i+1] = nuevo;
        }
        else{
            while((i>=0) && (nuevo.getCodigo()<contenidos[i].getCodigo())){
                i--;
            }
            
            int hijoainsertar = i+1;
            
            if(hijos[hijoainsertar].Llena()){
                
                llenos++;
                hijos[llenos] = hijos[llenos-1];
                
                for(int j = llenos-1;j>hijoainsertar;j--){
                    hijos[j] = hijos[j-1];
                    contenidos[j] = contenidos[j-1];
                }
                
                contenidos[hijoainsertar] = hijos[hijoainsertar].getContenidos()[t-1];
                hijos[hijoainsertar].setLlenos(t-1);

                NodoArbolB nuevoNodo = new NodoArbolB(t);
                
                int k;
                for(k = 0; k<t-1;k++){
                    nuevoNodo.getHijos()[k] = hijos[hijoainsertar].getHijos()[k+t];
                    nuevoNodo.getContenidos()[k] = hijos[hijoainsertar].getContenidos()[k+t];
                    hijos[hijoainsertar].getHijos()[k+t] = null; 
                    hijos[hijoainsertar].getContenidos()[k+t-1] = null;
                }
                
                hijos[hijoainsertar].getContenidos()[k+t-1] = null;
                
                nuevoNodo.getHijos()[t-1] = hijos[hijoainsertar].getHijos()[2*t-1];
                nuevoNodo.setLlenos(t-1);
                nuevoNodo.eshoja = hijos[hijoainsertar].eshoja;
                hijos[hijoainsertar+1] = nuevoNodo;
                
                if(nuevo.getCodigo() < contenidos[hijoainsertar].getCodigo()){
                    hijos[hijoainsertar].insertarTicket(nuevo);
                }
                else{
                    hijos[hijoainsertar+1].insertarTicket(nuevo);
                }
                hijos[hijoainsertar].getHijos()[k+t] = null;
                
            }
            else{
                hijos[hijoainsertar].insertarTicket(nuevo);
            }
        }
        
    }
    
    public boolean Llena(){
        return llenos == contenidos.length;
    }
    
    public NodoArbolB[] getHijos() {
        return hijos;
    }

    public void setHijos(NodoArbolB[] hijos) {
        this.hijos = hijos;
    }

    public Ticket[] getContenidos() {
        return contenidos;
    }

    public void setContenidos(Ticket[] contenidos) {
        this.contenidos = contenidos;
    }

    public int getLlenos() {
        return llenos;
    }

    public void setLlenos(int llenos) {
        this.llenos = llenos;
    }

    
}
