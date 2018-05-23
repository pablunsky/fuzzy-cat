/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author ciberveliz
 */
public class Dijkstra 
{
    private String codInicio;
    private String codDestino;
    private Grafo grafo;
    private InfoVertice info[];
    private ListaMini listaEstaciones;
    private double pesoMinimo;
    
    public Dijkstra(String codEstacionInicial, String codEstacionDestino, Grafo grafo)
    {
        this.listaEstaciones = new ListaMini();
        this.codInicio = codEstacionInicial;
        this.codDestino = codEstacionDestino;
        this.grafo = grafo;
        info = new InfoVertice[grafo.vertices.size];
        
        NodoVertice temp = grafo.vertices.primero;
        
        for(int i = 0; i < grafo.vertices.size;i++)
        {
            info[i] = new InfoVertice();
            info[i].setCodActual(temp.vertice.getCodigo());
            temp = temp.sig;
        }

        int in = this.getIndex(codInicio);
        info[in].setCodPredecesor("null");
        info[in].setFinalizado(true);
        info[in].setPesoAcumulado(0);
        
    }
    
    public double getPesoMinimo()
    {
        return this.pesoMinimo;
    }
    
    
    private void guardarRecorrido(String codigo)
    {
        this.listaEstaciones.insertarAlInicio(codigo);
        String codPre = this.info[this.getIndex(codigo)].getCodPredecesor();
        if(!codPre.equals("null"))
            guardarRecorrido(codPre);
    }
    
    public ListaMini obtenerRutaMinima()
    {
        //situarse en la estacion inicial
        boolean nO = grafo.vertices.estacionReptida(codInicio);
        boolean nF = grafo.vertices.estacionReptida(codDestino);
        if(!nO || !nF)
            return new ListaMini();
        NodoGrafo estacionInicial = grafo.vertices.getNodoEstacion(codInicio);
        recorrerAdyacentes(estacionInicial);
        
        guardarRecorrido(codDestino);
        this.pesoMinimo = this.info[this.getIndex(codDestino)].getPesoAcumulado();
        return this.listaEstaciones;
    }
    
    private void recorrerAdyacentes(NodoGrafo vertice)
    {
        int indiceP = getIndex(vertice.getCodigo());
        NodoArista aristaT = vertice.aristas.primero;
        
        while(aristaT != null)
        {
            int indice = getIndex(aristaT.arista.getDestino().getCodigo());
            if(indice == -1)
                return;
            if(!this.info[indice].isFinalizado())
            {            
                double peso = (aristaT.arista.getDistancia() * aristaT.arista.getTrafico()) + this.info[indiceP].getPesoAcumulado();

                if(this.info[indice].getPesoAcumulado() == 0 || this.info[indice].getPesoAcumulado() > peso)
                {  
                    this.info[indice].setPesoAcumulado(peso);
                    this.info[indice].setCodPredecesor(this.info[indiceP].getCodActual());
                }
            }
            
            aristaT = aristaT.sig;
            if(aristaT == null)
            {
                int indiceMenor = this.getIndiceMenorPesoNoFinalizado();
                if(indiceMenor != -1)
                {
                    this.info[indiceMenor].setFinalizado(true);
                    recorrerAdyacentes(this.grafo.vertices.getNodoEstacion(this.info[indiceMenor].getCodActual()));
                }

            }
        }
    }
    
    private int getIndex(String codigo)
    {
        for(int i = 0; i < this.info.length; i++)
        {
            if(this.info[i].getCodActual().equals(codigo))
                return i;
        }
        return -1;
    }
    
    private int  getIndiceMenorPesoNoFinalizado()
    {
        int indice = -1;
        double peso = 1000000;
        for(int i = 0; i < this.info.length;i++)
        {
            if(info[i].getPesoAcumulado() == 0.0)
                continue;
            if(peso > info[i].getPesoAcumulado() && !info[i].isFinalizado())
            {
                peso = this.info[i].getPesoAcumulado();
                indice = i;
            }
        }
        return indice;
    }
}
