/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import modelos.Patron;
import java.util.ArrayList;
import modelos.Patron;

/**
 *
 * @author Vanessa
 */
public class Instancias {
    private ArrayList<Patron> patrones; //lista de patrones que fonman las instancias
    private ArrayList<String> clases; //diferentes clases que recibe en las instancias
    private ArrayList<Integer> cantidadPorClase; //guardar cuantas veces aparece cada clase, cada clase en una posicion

    public Instancias(){
        this.patrones=new ArrayList<>();
        this.clases=new ArrayList<>();
        this.patrones=new ArrayList<>(); 
        this.cantidadPorClase = new ArrayList<>(); 
    }
    public void agregarPatron(Patron patron){
        patrones.add(patron); //agregar el patron a la lista de patrones
        if(!clases.contains(patron.getClase())){ //si la clase que tiene este patron, no exite, agrega una nueva clase
               this.clases.add(patron.getClase());
               this.cantidadPorClase.add(0); //agregamos una nueva posicion para la clase
        } 
          
          int posicion =this.clases.indexOf(patron.getClase()); //busca la posicion de la clase del patron, en la lista de clases
          this.cantidadPorClase.set(posicion, this.cantidadPorClase.get(posicion)+1);;// aumenta 1 en la posicion del arreglo que está la clase del patrón 
    }
    
    //agregar una lista de patrones
    public void agregarPatrones(ArrayList<Patron> patrones){
        for(Patron p: patrones){
           agregarPatron(p);
       }
    }
    public void eliminarPatron(int pos){
        Patron patron = patrones.get(pos);
        int posClase =this.clases.indexOf(patron.getClase()); //buscar la poscion de su clase en clases
        if(this.cantidadPorClase.get(posClase)>0)
           this.cantidadPorClase.set(posClase, this.cantidadPorClase.get(posClase)-1);;// disminuir 1 en la posicion del arreglo que está la clase del patrón  
        //System.out.println("   ElementoEliminado: " +patrones.get(pos).getVector()[0] + "\tclase: " +patrones.get(pos).getClase());
        this.patrones.remove(pos);
    }
    /**
     * @return the patrones
     */
    public ArrayList<Patron> getPatrones() {
        return patrones;
    }

    /**
     * @param patrones the patrones to set
     */
    public void setPatrones(ArrayList<Patron> patrones) {
        this.patrones = patrones;
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
     * @return the cantidadesDePatrones
     */
    public ArrayList<Integer> getCantidadPorClase() {
        return cantidadPorClase;
    }

    /**
     * @param cantidadesDePatrones the cantidadesDePatrones to set
     */
    public void setCantidadPorClase(ArrayList<Integer> cantidadesDePatrones) {
        this.cantidadPorClase = cantidadesDePatrones;
    }

    /**
     * @return the numClases
     */
    public int getNumClases() {
        return this.clases.size();
    }

    /**
     * @return the numPatrones
     */
    public int getNumPatrones() {
        return this.patrones.size();
    }
    
    
}
