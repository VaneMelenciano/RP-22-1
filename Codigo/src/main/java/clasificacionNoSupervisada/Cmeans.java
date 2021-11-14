/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificacionNoSupervisada;

import Herramientas.Instancias;
import static clasesSupervisadas.MinimaDistancia.distancia;
import java.util.ArrayList;
import modelos.Patron;
import modelos.PatronRepresentativo;

/**
 *
 * @author Vanessa
 */
public class Cmeans {
    private ArrayList<PatronRepresentativo> clusters; //patrones representativos
    private ArrayList<Patron> patrones; //patrones a clasificar
    private int numClases; //número de clases que se van a crear
    
    public Cmeans(Instancias patrones, int numClases){
        this.clusters = new ArrayList<>();
        this.patrones = patrones.getPatrones();
        this.numClases=numClases;
    }
    public void clasificar(){
        elegirClusters(); //se escogen n patrones al azar
        asignarClase(); //se clasifican los patrones con base a los Clusters elegidos anteriormente
        int con =0;
        while(compararClisters(ajustesClusters())){ //miestras sean diferentes
            //si entra aquí, es que ya actualizó los clusters a los nuevos ajustes
            asignarClase(); //se asigna nueva clase a cada patron con los nuevos Clusters
        }
        ////
        System.out.println("Últimos clusters");
        for(PatronRepresentativo p : this.clusters){
            System.out.print(p.getClase()+": ");
            for(Double u : p.getVector()){
                System.out.print(u + ", ");
            }System.out.println();
        }
       //// 
    }
    public void elegirClusters(){ //aleatorios sin repetirse
        int numPatrones = this.patrones.size(); //numero de patrones a clasificar
        ArrayList<Integer> posMedias = new ArrayList<>();//posiciones de las clusters en los patrones
        int primerPos = (int) (Math.random()*(numPatrones)+1); //posicion al azar de la primera media
        posMedias.add(primerPos);
        this.clusters.add(0, new PatronRepresentativo("cluster0", this.patrones.get(primerPos).getVector()));//agrega el patron en la posicion en las clusters
        
        for(int i=1; i<this.numClases; i++){ //genera el resto de posiciones
            int num = (int) (Math.random()*(numPatrones)+1);
            while(posMedias.contains(num)){ //si num ya existe en posMedias
                num = (int) (Math.random()*(numPatrones)+1);
            }
            posMedias.add(num);
            this.clusters.add(new PatronRepresentativo("cluster" + i, this.patrones.get(num).getVector()));
        } 
        ///
        /*System.out.println("Primeros");
        for(PatronRepresentativo p : this.clusters){
            System.out.print(p.getClase()+": ");
            for(Double u : p.getVector()){
                System.out.print(u + ", ");
            }System.out.println();
        }*/
        ///
    }
    
    public void asignarClase(){  //con base a los clusters y la distancia de estos con cada patron
        for(Patron p: this.patrones){
           double minDistancia = distancia(p.getVector(), this.clusters.get(0).getVector()); 
           int iMedia = 0; //posicion de la media a donde corresponde el patron
           //calcular la distancia entre el vector del nuevo patron con los representativos
            for(int y=1; y<this.clusters.size(); y++){
                double disAux = distancia(p.getVector(), this.clusters.get(y).getVector());
                if(minDistancia > disAux){
                    minDistancia = disAux;
                    iMedia = y;
                }
            }
            p.setClaseResultante(this.clusters.get(iMedia).getClase()); //le da al patron una claseResultante provisional
        }
    }
    
    public ArrayList<PatronRepresentativo> ajustesClusters(){
        //se elege un punto medio entre todas los patrones de la clase clisters0, clisters1, clisters2 ... n
        ArrayList<PatronRepresentativo> auxClusters = new ArrayList<>();
        int pos = 0;
        auxClusters.add(new PatronRepresentativo("cluster" + pos, this.patrones.get(0).getVector()));
        for (int x=1; x<this.patrones.size();x++){ //iniciando el 1, el 0 se agrego arriba
           Patron aux = this.patrones.get(x);
           int posicion = buscarClase(aux, auxClusters); //busca si su clase ya existe
             if(posicion!=-1)//si exite agrega ese elemento al representativo correspondiente
                auxClusters.get(posicion).acumular(aux);
             else{//si no existe, crea un representativo (para una nueva clase)
                 pos++;
                 auxClusters.add(new PatronRepresentativo("cluster" + pos, aux.getVector()));
             }
        }
        for(PatronRepresentativo aux: auxClusters){ //calcula el promedio de los representativos
            aux.promediar(); 
            //double roundDbl = Math.round(aux*10000.0)/10000.0; //redondea a 4 decimales
        }
        //compararClisters(auxClusters);
        /*System.out.println("Nuevos");
        for(PatronRepresentativo p : auxClusters){
            System.out.print(p.getClase()+": ");
            for(Double u : p.getVector()){
                System.out.print(u + ", ");
            }System.out.println();
        }*/
        
        return auxClusters;
    }
    
    public boolean compararClisters(ArrayList<PatronRepresentativo> auxClusters ){
        //retorna true si son diferentes, false en caso contrario (iguales)
        for(int x=0; x< auxClusters.size();x++){
            double[] vectorN = auxClusters.get(x).getVector();
            for(int i=0; i<vectorN.length; i++){
                if(vectorN[i]!=this.clusters.get(x).getVector()[i]){ //si es diferente uno de sus valores
                    this.clusters = (ArrayList<PatronRepresentativo>) auxClusters.clone();
                    return true;
                }
            }
        } return false;
    }

    public static int buscarClase(Patron aux, ArrayList<PatronRepresentativo> auxClusters){
        int posicion=-1;
        boolean encontrado = false; //bandera para saber si exite la clase representativa de la nueva instancia
           for(int i=0; i<auxClusters.size(); i++){ //recorre los representativos
               if(auxClusters.get(i).getClase().equals(aux.getClaseResultante())){ //compara la clase de la nueva instancia con la clase de los representativos
                   encontrado=true;
                   posicion=i;
                   break;
               }
           }
        return posicion;
    }
    /**
     * @return the clusters
     */
    public ArrayList<PatronRepresentativo> getMedias() {
        return clusters;
    }

    /**
     * @param clusters the clusters to set
     */
    public void setMedias(ArrayList<PatronRepresentativo> clusters) {
        this.clusters = clusters;
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
    public Instancias getInstancias(){
        return new Instancias(this.patrones);
    }
    
}
