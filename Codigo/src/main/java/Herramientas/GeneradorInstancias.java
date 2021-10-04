/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import modelos.Patron;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vanessa
 * Clase para seleccionar cierta cantidad de instancias, del total recibidas
 * 
 * El factor que recibe son de donde se borran las instancias
 * El porcentaje es el total de instancias que se decea conservar
 * Las caracteristicas, son las que se desean conservar en cada instancia
 */

public class GeneradorInstancias { 
    private Instancias instanciasOriginales;
    private Instancias nuevasInstancias;
    
    public GeneradorInstancias(Instancias instancias){
        this.instanciasOriginales=instancias;
        this.nuevasInstancias=new Instancias();
    }
    
    //las caracteristicas son las que quiere tomar de los vectores de cada patron
    public void generarInstancia(int[] caracteristicas, double porcentaje, Factor factor){
         filtrarCaracteristicas(caracteristicas); //caracteristicas de las instancias originales ya filtradas y guardadas en un nuevo ArrayList
         factor(porcentaje, factor);
    }
    public void generarInstancia(double porcentaje, Factor factor){ //sin filtrado de caracteristicas
        factor(porcentaje, factor);
    }
    public void generarInstancia(double porcentaje){ //sin filtrado de caracteristicas y factor automatico RANDOM
        factor(porcentaje, Factor.RANDOM);
    }
    
    public void factor(double porcentaje, Factor factor){
        switch(factor){
           case RANDOM:{
               eliminarAleatorio(porcentaje);
               break;
           }
           case PRIMEROS:{
               eliminarPrimeros(porcentaje);
           break;
           }
           case ENMEDIO:{
               eliminarEnMedio(porcentaje);
               break;
           }
           case ULTIMOS:{
               eliminarUltimos(porcentaje);
           break;
           }
           default:{System.out.println("No se reconocer el factor de selección");}
       }
    }
    public void filtrarCaracteristicas(int[] caracteristicas){ 
         for(Patron p: this.instanciasOriginales.getPatrones()){ //recorre los patrones de las instancias originales
             double[] vector = p.getVector(); //vector del patron original
             double[] nuevoVector = new double[caracteristicas.length]; //nuevo vector que tendrá solo las caracteristicas que queremos
             if(caracteristicas.length>vector.length || caracteristicas[caracteristicas.length-1]>vector.length ){
                 //compruba que las caracteristicas que se desean seleccionar, esten dentro del margen de los atributos del vector original
                 System.out.println("Selección de características incorrecto");
             }
             for(int i=0; i < caracteristicas.length; i++){//filtrado
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
            for (int j = 0; j < cantidadEliminar; j++) {
                this.nuevasInstancias.eliminarPatron(posMin);
                posMin++;
            }
        }
    }
    
    public void eliminarEnMedio(double porcentaje){
        for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
            int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
            int elementos = this.instanciasOriginales.getCantidadPorClase().get(i);
            int posMin = (int)Math.round((elementos-cantidadEliminar)/2) + nMinimo(i); //calcula una posicion donde iniciar a eliminar que se encuentre en medio
           
            for (int j = 0; j < cantidadEliminar; j++) {
                this.nuevasInstancias.eliminarPatron(posMin);
                posMin++;
            }
        }
    }
    public void eliminarUltimos(double porcentaje){
        for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
            int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
            int posMax = nMaximo(i)-1; //posicion minima y maxima de las instancias que se van a eliminar
            for (int j = 0; j < cantidadEliminar; j++) { 
                nuevasInstancias.eliminarPatron(posMax);//posicion aleatoria entre el rango predefinido
                posMax--;
            }
        }
    }
    public void eliminarAleatorio(double porcentaje){
        Random ran = new Random(); 
            for (int i = this.instanciasOriginales.getNumClases() - 1; i >= 0; i--) { //i recorre el numero de clases de forma descendiente
                int cantidadEliminar = calculaCantidad(i, porcentaje); //numero de instancia a eliminar 
                int posMax = nMaximo(i), posMin = nMinimo(i); //posicion minima y maxima de las instancias que se van a eliminar
                for (int j = 0; j < cantidadEliminar; j++) {
                    int pos =(int)(Math.random()*(posMax-posMin))+posMin; //posicion aleatoria entre el rango predefinido
                    nuevasInstancias.eliminarPatron(pos);
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
        int aEliminar = (int)Math.round((elementosClase*porcentaje)/100); //numero de instacias a eliminar
        //int aEliminar = (int)Math.round((elementosClase*Math.abs(100-porcentaje))/100); //numero de instacias a eliminar
        if (aEliminar>=elementosClase){
          return elementosClase-1;
        }
        return aEliminar;
    }
    public Instancias getNuevasInstancias() {
        return nuevasInstancias;
    }
    
    
}
