 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import modelos.Patron;

/**
 *
 * @author Vanessa
 */
// Objetivo: Clase apoyo para hacer más sencillo la acumulación de los elementos, es decir, 
// ayuda a ir construyendo ese elemento representativo de cada una de las clases, utilizando la misma estructura de Patron.
// 
public class PatronRepresentativo extends Patron{
    
    private int cantidad; //para contar cuantas veces se hace el acumulamiento, para CADA UNA DE LAS CARACTERÍSTICAS
    
    public PatronRepresentativo(String clase, double[] vector) {
        super(clase, vector);
        this.cantidad = 1;
    }
    //Constructor que recibe Patron, para usarlo en MinimaDistancia
    public PatronRepresentativo(Patron p) {
        super(p.getVector().length);
        super.setClase(p.getClase());
        super.setVector(p.getVector());
        this.cantidad = 1;
        //promediar(p);
    }
    
    
    public void acumular(Patron aux){
        //proceso interativo en el que acumulamos la información
        // Ejemplo: 
        /*
                    aux: 2, 9, 7, 1
        super.getVector: 2, 7, 9, 1
        Resultado: suma al vector, los valores de aux
        nuevo vector :   4, 16, 16, 2
        */
        for(int x=0; x<super.getVector().length;x++){
           // System.out.println("Vector: " + getVector()[x] + "   Auxiliar: " + aux.getVector()[x]);
            super.getVector()[x]+=aux.getVector()[x];
           // System.out.println("Suma: " + getVector()[x]);
        }
        setCantidad(getCantidad() + 1);
    }   
    
    public void promediar(){
        for(int x=0; x<super.getVector().length;x++){
            super.getVector()[x]/=getCantidad();
            super.getVector()[x] = Math.round(super.getVector()[x]*10000.0)/10000.0; //redondea a 4 decimales
        }
        //Ejemplo
        /*
        Divide cada valor de vector entre la cantidad de veces que se le ha sumado:
        Si cantidad : 2
        vector : 4, 16, 16, 2
        nuevo vector : 2, 8, 8, 0
        */
        cantidad=1;
    }
    
    public void promediar(Patron p){
       for(int x=0; x<p.getVector().length;x++){ 
           super.getVector()[x]/=getCantidad();
       }
       cantidad=1;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
