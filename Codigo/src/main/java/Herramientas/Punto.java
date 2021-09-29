/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;
import java.util.Arrays;

/**
 *
 * @author Vanessa
 */
public class Punto implements Comparable<Punto>{
//Clase que ayuda en la clasificación Knn
    private String clase;
    private double distancia;
    public Punto(){
        
    }
    public Punto(String clase, double distancia){
        this.clase=clase;
        this.distancia=distancia;
    }
    @Override
        public int compareTo(Punto p) {
            if (this.distancia < p.distancia) return -1;
            if (this.distancia > p.distancia) return 1;
            return 0;
        }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
}
