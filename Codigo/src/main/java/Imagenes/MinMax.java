/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import Herramientas.Instancias;
import static clasesSupervisadas.MinimaDistancia.distancia;
import java.util.ArrayList;
import modelos.Patron;
import modelos.PatronRepresentativo;

/**
 *
 * @author Vanessa
 */
//FUNCIONAMIENTO
    //recibe las instancias
    //determina centro (media de las instancias)
    //calcula dos primeros centroides (los más alejados)
    //calcula la min distancia de cada instancia a cada centroide, y eligue la menor (lista de minimos)
    //de la lista de minimos, el que tenga más es "d", es decir, la instancia más lejana de los dos centroides
    //si "d" es mayor o igual que el umbral, se debe hacer otro centroide: el cluster3 toma el vector de 
            //la instancia más lejana de los dos primeros centroides 
    //se vuelven a hacer la lista de minimos, ahora con los 3 clusters y el proceso se repite
            //hasta que "d" sea menor que el umbral, es decir, que la instancia más lejana
            //de los clusters calculados, no supere el umbral
public class MinMax {
    private ArrayList<Patron> instancias; //instancias que se reciben 
    private Patron central;
    private ArrayList<Patron> centroidesBase;
    private ArrayList<Integer> posicionesCentroides; 
    private double max, a, umbral;
    
    public MinMax(Instancias instancias, double umbral){
       this.instancias=instancias.getPatrones();
       this.centroidesBase = new ArrayList<>();
       this.posicionesCentroides = new ArrayList<>();
       this.umbral=umbral;
       proceso();
    }
    
    public MinMax(ArrayList<Patron> instancias, double umbral){
       this.instancias=instancias; 
       this.centroidesBase = new ArrayList<>();
       this.posicionesCentroides = new ArrayList<>();
       this.umbral=umbral;
       proceso();
    }
    
    public void proceso(){
       //determina centro (media de las instancias)
       encontrarCentral();
       //calcular los primeros dos centroides
       centroidesIniciales();
       
       //calcular minima distancias de cada instancia a cada centroide
       //y con el umbral calcular si es necesario otro centroide
       boolean nuevoC = true;
       while(nuevoC){ //hasta que ya no sea necesario otro centroide
            int pos = calcularMinimos(); //retorna la posicion de la más grande distancia entra una instancia y un centroide
           if (this.max>=this.umbral*this.a){
                // creamos un nuevo centroide
                crearNuevoCentroide(pos);
                nuevoC = true;
            }else {
                nuevoC = false;
            }
       }
       //CLASIFICACION
        asignarClases();
    }
    
    public void encontrarCentral(){
        //acular los atributos de todas las instancias
        int atributos = this.instancias.get(0).getVector().length;
        double[] vector = new double[atributos];
        for(Patron p : this.instancias){
            for(int i=0; i<atributos; i++)
                vector[i]+=p.getVector()[i];
        }
        //dividir el vector de la suma entre el numero de instancias
        for(int i=0; i<atributos; i++)
            vector[i]/=this.instancias.size();
        
        this.central = new Patron(vector);
    }
    
    public void centroidesIniciales(){
        //buscar el primero: el más alejado del centro
        double distancia= distancia(central.getVector(),instancias.get(0).getVector());
        int posicion1=0;
        for(int i=1; i<this.instancias.size(); i++){
            double auxDis = distancia(central.getVector(),instancias.get(i).getVector());
            if(auxDis>distancia){
                distancia=auxDis;
                posicion1=i;
            }
        }
        Patron lejano1= new Patron(this.instancias.get(posicion1));
        lejano1.setClase("Cluster0");
        this.centroidesBase.add(lejano1);
            this.instancias.remove(posicion1); //AQUI
        
        //buscar el segundo: el más alejado al lejano1
        distancia=distancia(lejano1.getVector(),instancias.get(0).getVector());
        int posicion2=0;
        for(int i=1;i<this.instancias.size();i++){
            double auxDis = distancia(central.getVector(),instancias.get(i).getVector());
            if(auxDis>distancia){
                distancia=auxDis;
                posicion2=i;
            }
        }
        Patron lejano2 =new Patron(this.instancias.get(posicion2));
        lejano2.setClase("Cluster1");
        this.centroidesBase.add(lejano2);
            this.instancias.remove(posicion2); //AQUI
            
        this.a=distancia(centroidesBase.get(0).getVector(),centroidesBase.get(1).getVector())/2;
    }
    
    public int calcularMinimos(){
        //calcula minimos
        ArrayList<Double> minimos = new ArrayList<>();
        for (Patron aux:this.instancias){
        //debe ignorar los centroides/////////////////////
          double menor = distancia(aux.getVector(),centroidesBase.get(0).getVector());
          for (int x=1; x < this.centroidesBase.size();x++){
             double dd = distancia(aux.getVector(),centroidesBase.get(x).getVector());
              if (dd<menor){
               menor = dd;
              }
          }
         minimos.add(menor);
        }
        
        //determina el mayor de los minimos y su posicion
        this.max = minimos.get(0);
        int pos = 0;
        for(int i=0; i<minimos.size(); i++){
            if(minimos.get(i)>this.max){
                this.max = minimos.get(i);
                pos = i;
            }
        }
        return pos;
    }
    
    public void crearNuevoCentroide(int pos){
        int id = this.centroidesBase.size();
        // agregamos el nuevo centroide
        Patron aux = new Patron(this.instancias.get(pos));
        aux.setClase("Cluster"+id);
        this.centroidesBase.add(aux);
        // eliminamos la instancia iI
        this.instancias.remove(pos); //AQUI
    }
    
    public void asignarClases(){  //con base a los clusters y la distancia de estos con cada patron
        for(Patron p: this.instancias){
            double minDistancia = distancia(p.getVector(), this.centroidesBase.get(0).getVector()); 
            int iMedia = 0; //posicion de la media a donde corresponde el patron
            //calcular la distancia entre el vector del nuevo patron con los clusters
             for(int y=1; y<this.centroidesBase.size(); y++){
                 double disAux = distancia(p.getVector(), this.centroidesBase.get(y).getVector());
                 if(minDistancia > disAux){
                     minDistancia = disAux;
                     iMedia = y;
                 }
             }
             p.setClaseResultante(this.centroidesBase.get(iMedia).getClase()); //le da al patron una claseResultante provisional
        }
    }
    public Instancias getInstancias(){
        return new Instancias(this.instancias);
    }
    public ArrayList<Patron> getcentroidesBase(){
        return centroidesBase;
    }
    public ArrayList<PatronRepresentativo> getcentroidesBase1(){ //castea los centroides a patrones representativos
        ArrayList<PatronRepresentativo> patrones = new ArrayList<>();
        for(Patron p: this.centroidesBase){
            patrones.add(new PatronRepresentativo(p));
        }
        return patrones;
    }
}
