/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */
public class MatrizConfusion {
    
    private ArrayList<Patron> instancias;
    private int[][] matriz;
    private ArrayList<String> clases; //diferentes clases que recibe en las instancias
    private double sumaDiagonal; //suma de las instancias clasificadas correctamente
    
    /*public MatrizConfusion(ArrayList<Patron> instanciass){ //se resiben la lista de datos YA CLASIFICADA, con su clase resultante   
        this.matriz=null;
        this.clases=new ArrayList<>();
        this.sumaDiagonal=0;
        this.instancias = instanciass;
        this.construirMatriz(instancias); 
    }*/
    public MatrizConfusion(){
        this.matriz=null;
        this.clases=new ArrayList<>();
        this.sumaDiagonal=0;
        this.instancias = new ArrayList<>();
    }
    
    public void construirMatriz(ArrayList<Patron> instanciass){
        this.instancias = instanciass;
        /*
                        Setosa      Versicolor      Virginica
        Setosa                        
        Versicolor         
        virginica          
        */
        // Saber cuales son las clases diferentes exiten en las instancias que recibe
        for(Patron pa: instancias){
           if(!clases.contains(pa.getClase())){
               clases.add(pa.getClase());
           }
       }   
        setMatriz(new int[getClases().size()][getClases().size()]); //da a la matriz las dimensiones, dependiendo de las clases exixtentes
       for(Patron p: getInstancias()){
           int verdaderaClase = clases.indexOf(p.getClase()); // posicion de la verdadera clase de la instancia, en el ArrayList de clases
           int claseResultante = clases.indexOf(p.getClaseResultante()); // posicion de la clase resultante de la instancia, en el ArrayList de clases
            matriz[verdaderaClase][claseResultante]++;
           if(verdaderaClase==claseResultante){ //si está bien clasificada
                sumaDiagonal++; 
           }
       }
    }
    
    public double calcularEfectividad(){
        //porcentaje (multiplicar la suma por 100, entre el total de instancias resibidas)
        return sumaDiagonal*100/getInstancias().size(); 
    }

    @Override
    public String toString() {
        String auxMatriz = "\t";
        for(String clase: clases){ //primera fila
            auxMatriz+=clase+"\t";
        }
         auxMatriz+="\n";
        for(int i=0;i<clases.size();i++){
                auxMatriz+=clases.get(i)+"\t";
            for(int j=0;j<clases.size();j++){
             auxMatriz+=getMatriz()[i][j]+"\t";
            }
            auxMatriz+="\n";
        }
        auxMatriz+= "\nInstancias clasificadas correctamente: " + getSumaDiagonal();
        auxMatriz+= " \nEfectividad : " + calcularEfectividad() + "%";
        return auxMatriz;
    }
    
     //PARA IRIS
    /*public String toString() {
        String auxMatriz = "\t\t";
        for(String clase: clases){ //primera fila
            auxMatriz+=clase+"\t";
        }
         auxMatriz+="\n";
        for(int i=0;i<clases.size();i++){
                auxMatriz+=clases.get(i)+"\t";
            for(int j=0;j<clases.size();j++){
             auxMatriz+=getMatriz()[i][j]+"\t\t";
            }
            auxMatriz+="\n";
        }
        auxMatriz+= "\n" + "Instancias clasificadas correctamente: " + getSumaDiagonal();
        auxMatriz+= " \nEfectividad : " + calcularEfectividad() + "%";
        return auxMatriz;
    }*/
    

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
     * @return the clases
     */
    public ArrayList<String> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(ArrayList<String> clases) {
        this.clases = clases;
    }

    /**
     * @return the sumaDiagonal
     */
    public double getSumaDiagonal() {
        return sumaDiagonal;
    }

    /**
     * @param sumaDiagonal the sumaDiagonal to set
     */
    public void setSumaDiagonal(int sumaDiagonal) {
        this.sumaDiagonal = sumaDiagonal;
    }
    
    
}
