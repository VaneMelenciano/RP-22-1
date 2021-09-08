/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Vanessa
 */
public class Patron {
    private String clase;
    private String claseResultante;
    private double vector[]; //características
    
    public Patron(String clase, double vector[]){ 
        this.clase = clase;
        this.claseResultante = "";
        this.vector = vector.clone();
    }
    public Patron(double vector[]){ 
        this.clase = "";
        this.claseResultante = "";
        this.vector = vector.clone();
    }
    public Patron (Patron p){
        this.clase = p.getClase();
        this.vector = p.getVector().clone();
        this.claseResultante = "";
    }
    public Patron(int n) {
        this.vector = new double[n];
        this.clase = "";
        this.claseResultante = "";
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
     * @return the claseResultante
     */
    public String getClaseResultante() {
        return claseResultante;
    }

    /**
     * @param claseResultante the claseResultante to set
     */
    public void setClaseResultante(String claseResultante) {
        this.claseResultante = claseResultante;
    }

    /**
     * @return the vector
     */
    public double[] getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(double[] vector) {
        this.vector = vector;
    }
    
    
}
