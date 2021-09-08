/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesSupervisadas;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */
public class MatrizConfusion {
    
    private ArrayList<Patron> instancias;
    private int[][] matriz;
    private int efectividad;
    
    public MatrizConfusion(ArrayList<Patron> instancias){ //se resiben la lista de datos YA CLASIFICADA, con su clase resultante
        this.instancias = instancias; 
        this.matriz=null;
    }
    
    public void construirMatriz(){
        /*
                        Setosa      Versicolor      Virginica
        Setosa                        
        Versicolor         
        virginica          
        */
        // Saber cuantas clases existen
        
    }
    public void calcularEfectividad(){
        //suma de la diagonal de la matriz
        //porcentaje (multiplicar la suma por 100, entre el total de instancias resibidas)
        
    }

    /**
     * @return the instancias
     */
    public ArrayList<Patron> getInstancias() {
        return instancias;
    }

    /**
     * @param instancias the instancias to set
     */
    public void setInstancias(ArrayList<Patron> instancias) {
        this.instancias = instancias;
    }

    /**
     * @return the matriz
     */
    public int[][] getMatriz() {
        return matriz;
    }

    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    /**
     * @return the efectividad
     */
    public int getEfectividad() {
        return efectividad;
    }

    /**
     * @param efectividad the efectividad to set
     */
    public void setEfectividad(int efectividad) {
        this.efectividad = efectividad;
    }
    
    
}
