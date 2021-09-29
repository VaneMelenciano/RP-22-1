/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import data.Patron;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vanessa
 * Clase para seleccionar cierta cantidad de instancias, del total recibidas
 */

public class GeneradorInstancias { 
    private Instancias instanciasOriginales;
    private Instancias nuevasInstancias;
    
    public GeneradorInstancias(Instancias instancias){
        this.instanciasOriginales=instancias;
        this.nuevasInstancias=new Instancias();
    }
    
    //las caracteristicas son las que quiere tomar de los vectores de cada patron
    public void generarInstancia(int[] caracteristicas, double porcentaje, String factor){
         filtrarCaracteristicas(caracteristicas); //caracteristicas de las instancias originales ya filtradas y guardadas en un nuevo ArrayList
         factor(porcentaje, factor);
    }
    public void generarInstancia(double porcentaje, String factor){ //sin filtrado de caracteristicas
        factor(porcentaje, factor);
    }
    public void generarInstancia(double porcentaje){ //sin filtrado de caracteristicas y factor automatico RANDOM
        factor(porcentaje, "RANDOM");
    }
    
    public void factor(double porcentaje, String factor){
        switch(factor){
           case "RANDOM":{
               eliminarAleatorio(porcentaje);
               break;
           }
           case "PRIMEROS":{
               eliminarPrimeros(porcentaje);
           break;
           }
           case "ENMEDIO":{
               eliminarEnMedio(porcentaje);
               break;
           }
           case "ULTIMOS":{
               eliminarUltimos(porcentaje);
           break;
           }
           default:{System.out.println("No se reconocer el factor de selección");}
       }
    }
    public void filtrarCaracteristicas(int[] caracteristicas){ //filtrado
         for(Patron p: this.instanciasOriginales.getPatrones()){ //recorre los patrones de las instancias originales
             double[] vector = p.getVector(); //vector del patron original
             double[] nuevoVector = new double[caracteristicas.length]; //nuevo vector que tendrá solo las caracteristicas que queremos
             for(int i=0; i < caracteristicas.length; i++){
               nuevoVector[i]=vector[caracteristicas[i]];
             } 
             Patron nuevoPatron = new Patron(p.getClase(), nuevoVector);
             nuevasInstancias.agregarPatron(nuevoPatron);
         } 
    }
    public void eliminarPrimeros(double porcentaje){
        for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
            int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
            int posMin = nMinimo(i); //posicion minima y maxima de las instancias que se van a eliminar
            //System.out.println("   "+posMin);
            for (int j = 0; j < cantidadEliminar; j++) {
                //int pos =(int)(Math.random()*(posMax-posMin))+posMin; //posicion aleatoria entre el rango predefinido
                //System.out.println(this.instanciasOriginales.getPatrones().get(posMin).getClase() + "  " + this.instanciasOriginales.getPatrones().get(posMin).getVector()[1]);
                this.nuevasInstancias.eliminarPatron(posMin);
                //System.out.println("   pos: " + pos);
                posMin++;
            }
        }
    }
    
    public void eliminarEnMedio(double porcentaje){
        
    }
    public void eliminarUltimos(double porcentaje){
        for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
            //System.out.println("i: " + i);
            int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
            //System.out.println("   CantidadE: " + cantidadEliminar );
            int posMax = nMaximo(i)-1; //posicion minima y maxima de las instancias que se van a eliminar
            //System.out.println("   "+posMax);
            //System.out.println("   auxRan: " + auxRan + "\telemento: " + this.instanciasOriginales.getPatrones().get(auxRan-1).getVector()[0] +"\n   auxRan1: " + auxRan1 + "\telemento: " + this.instanciasOriginales.getPatrones().get(auxRan1-1).getVector()[0] );
            for (int j = 0; j < cantidadEliminar; j++) {
                //int pos =(int)(Math.random()*(posMax-posMin))+posMin; //posicion aleatoria entre el rango predefinido
                nuevasInstancias.eliminarPatron(posMax);
                //System.out.println("   pos: " + pos);
                posMax--;
            }
        }
    }
    public void eliminarAleatorio(double porcentaje){
        Random ran = new Random(); 
            for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
                //System.out.println("x: " + x);
                int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
                //System.out.println("   CantidadE: " + cantidadEliminar);
                int posMax = nMaximo(i), posMin = nMinimo(i); //posicion minima y maxima de las instancias que se van a eliminar
                //System.out.println("   auxRan: " + auxRan + "\telemento: " + this.instanciasOriginales.getPatrones().get(auxRan-1).getVector()[0] +"\n   auxRan1: " + auxRan1 + "\telemento: " + this.instanciasOriginales.getPatrones().get(auxRan1-1).getVector()[0] );
                for (int j = 0; j < cantidadEliminar; j++) {
                    int pos =(int)(Math.random()*(posMax-posMin))+posMin; //posicion aleatoria entre el rango predefinido
                    nuevasInstancias.eliminarPatron(pos);
                    //System.out.println("   pos: " + pos);
                    posMax--;
                }
            }
    }
    private int nMinimo(int x) { 
        int nm=0;
        for (int i = x; i < instanciasOriginales.getNumClases(); i++) { 
            nm+=instanciasOriginales.getCantidadPorClase().get(i);  //suma las instancias recorridas de abajo a arriba hasta la clase en la posicion x
        }
        return (instanciasOriginales.getNumPatrones()-nm);
    }
    private int nMaximo(int x) {
        int nma = 0;
        for (int i = 0; i <= x; i++) {
            nma=nma+instanciasOriginales.getCantidadPorClase().get(i);  //suma las instancias recorridas de arriba hacia abajo hasta la clase en la posicion x 
        }
        return nma;
    }
    public int calculaCantidad(int posClase, double porcentaje) { 
        int elementosClase = this.instanciasOriginales.getCantidadPorClase().get(posClase); //numero de elementos de la clase que está en la posicion posClase
        //System.out.println("   Clase " + this.instanciasOriginales.getClases().get(posClase) + ":  " + elementosClase);
        int aEliminar = (int)Math.round((elementosClase*Math.abs(100-porcentaje))/100); //numero de instacias a eliminar
        
        if (aEliminar>=elementosClase){
          return elementosClase-1;
        }
        return aEliminar;
    }
    public Instancias getNuevasInstancias() {
        return nuevasInstancias;
    }
    
    
}
