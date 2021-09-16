/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesSupervisadas;

import Herramientas.Punto;
import data.Patron;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Vanessa
 */


public class KNN {
    private ArrayList<String> clases; //diferentes clases que recibe en las instancias
    private MatrizConfusion mc; //para crear la matriz de confusión después de clasificar
    private ArrayList<Patron> instancias; //recibidas para entrenar
    private ArrayList<Punto> distancias; //para guardar la distancia de cada punto a clasificar, a cada instancia
    private int k;
    
    public KNN(int k){
        clases = new ArrayList<>();
        mc = new MatrizConfusion();
        this.instancias = new ArrayList<>();
        this.k=k;
    }
    
    public void entrenar(ArrayList<Patron> instancias){
        // Saber cuales son las clases diferentes exiten en las instancias que recibe
        this.instancias=instancias;
        for(Patron pa: instancias){
           if(!clases.contains(pa.getClase())){
               clases.add(pa.getClase());
           }
       }
    }
    
    public void clasificar(Patron patron){
        distancias = new ArrayList<Punto>();
        for(Patron p: instancias){
            distancias.add(new Punto(p.getClase(),distancia(patron.getVector(), p.getVector()))); //calcular distancia del patron a cada instancia guardada
        }
         Collections.sort(distancias); //ordena la lista de puntos por la distancia de menor a mayor
         
        int conClases[] = new int[this.clases.size()]; //arreglo que va a guardar cuantas veces aparece cada clase, cada clase en una posicion
        
        for(Punto p:distancias){ // recorrer los distancias ordenadas
            int posicion =this.clases.indexOf(p.getClase());
            conClases[posicion]++; //aumenta 1 en la posicion del arreglo de la clase
            
            if(conClases[posicion]==k){ //cuando alguno de las clases llega a k
                patron.setClaseResultante(this.clases.get(posicion)); //le da el nombre de su "ClaseResultante" al patron que se calificó
                break;
            }
        }
    }
    
    public void clasificar(ArrayList<Patron> patrones) { //En caso de recibir una lista de patrones, va clasificando uno por uno
       for(Patron p: patrones){
           clasificar(p);
       }
        mc.construirMatriz(patrones); 
        //se mandan los patrones ya clasificados para crear la matriz de confusion
        //los patrones que mandamos, tiene ya su clase resultante y clase original
    }
    
    
    
    public static double distancia(double[] v1, double[] v2) {
        //pow ((A-B)^2  + (A1-B1)^2 + (A3-B3)^2 + (A4-A4)^2)
        double dis = 0; //se irá sumando las distancias entre cada punto
        for(int x=0; x<v1.length; x++){
            dis+=Math.pow((v1[x]-v2[x]), 2);
        }
        dis = Math.sqrt(dis);
        return dis;
    }
    
    /**
     * @return the mc
     */
    public MatrizConfusion getMc() {
        return mc;
    }

    /**
     * @param mc the mc to set
     */
    public void setMc(MatrizConfusion mc) {
        this.mc = mc;
    }
    
}
