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
    private ArrayList<Patron> instancias; //instancias que se usan para crear los centroides
    private ArrayList<Patron> instanciasDisponibles; //no incluyen centroides
    private Patron central;
    private ArrayList<Patron> centroidesBase;
    private ArrayList<Integer> posicionesCentroides; 
    private double max, mitad, umbral;
    
    public MinMax(Instancias instancias, double umbral){
       this.instancias=instancias.getPatrones();
       this.instanciasDisponibles=instancias.getPatrones();
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
       //asignarClases();
       //int pos = calcularMinimos();
       //System.out.println(this.instancias.get(pos).getVector()[0] + "  " + this.instancias.get(pos).getVector()[1]);
       //calcular minima distancias de cada instancia a cada centroide
       //y con el umbral calcular si es necesario otro centroide
       
       //crearNuevoCentroide(59);
       boolean nuevoC = true;
       while(nuevoC){ //hasta que ya no sea necesario otro centroide
           int pos = calcularMinimos(); //retorna la posicion de la más grande distancia entra una instancia y un centroide
           if (this.max>=this.umbral*this.mitad){
                // creamos un nuevo centroide
                
                crearNuevoCentroide(pos);
                nuevoC = true;
                //asignarClases();
            }else {
                nuevoC = false;
            }
        }
       //CLASIFICACION
        asignarClases();
        //System.out.println(" - " + instancias.get(117).getVector()[0] + "," +instancias.get(117).getVector()[1] + "  " + instancias.get(117).getClaseResultante());
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
        //System.out.println(distancia + "   0");
        int posicion1=0;
        for(int i=1; i<this.instancias.size(); i++){
            double auxDis = distancia(central.getVector(),instancias.get(i).getVector());
            //System.out.println(auxDis + "  " + i + " - " + instancias.get(i).getVector()[0] + "," +instancias.get(i).getVector()[1]);
            if(auxDis>distancia){
                distancia=auxDis;
                posicion1=i;
                //System.out.println("AQUI");
            }
        }
        Patron lejano1= new Patron(this.instancias.get(posicion1));
        //System.out.println("    AQUI2 "+posicion1);
        lejano1.setClaseResultante("Cluster1");
        this.centroidesBase.add(lejano1);
            //this.instanciasDisponibles.remove(posicion1); //AQUI
            this.posicionesCentroides.add(posicion1);
            this.instancias.get(posicion1).setClaseResultante("Cluster1"); //----
        
            
            
        //buscar el segundo: el más alejado al lejano1
        distancia=distancia(lejano1.getVector(),instancias.get(0).getVector());
        //System.out.println(distancia + "   0");
        int posicion2=0;
        for(int i=1;i<this.instancias.size();i++){
            double auxDis = distancia(lejano1.getVector(),instancias.get(i).getVector());
            //System.out.println(auxDis + "  " + i + " - " + instancias.get(i).getVector()[0] + "," +instancias.get(i).getVector()[1]);
            if(auxDis>distancia){
                distancia=auxDis;
                posicion2=i;
                //System.out.println("AQUI");
            }
        }
        Patron lejano2 =new Patron(this.instancias.get(posicion2));
        lejano2.setClaseResultante("Cluster2");
        //System.out.println("    AQUI2 "+posicion2);
        this.centroidesBase.add(lejano2);
             this.instancias.get(posicion2).setClaseResultante("Cluster2");

            
            this.posicionesCentroides.add(posicion2);
        this.mitad=distancia(centroidesBase.get(0).getVector(),centroidesBase.get(1).getVector())/2;
        //System.out.print(this.centroidesBase.size());
        
        
        //this.instanciasDisponibles.remove(posicion1); //AQUI
        //posicion2 = posicion1<posicion2 ? posicion2-1 : posicion2; //aumenta 1 a la posicion2 si la posicion1 está antes que ella
        //this.instanciasDisponibles.remove(posicion2);
    }
    
    public int calcularMinimos(){
        //calcula minimos
        /*ArrayList<Double> minimos = new ArrayList<>();
        //for (Patron aux:this.instancias){
        for(int i=0; i<this.instancias.size(); i++){
            if(!this.posicionesCentroides.contains(i)){
                //debe ignorar los centroides/////////////////////
                  double menor = distancia(this.instancias.get(i).getVector(),centroidesBase.get(0).getVector());
                  for (int x=1; x < this.centroidesBase.size();x++){
                     double dd = distancia(this.instancias.get(i).getVector(),centroidesBase.get(x).getVector());
                      if (dd<menor){
                       menor = dd;
                      }
                  }
                 minimos.add(menor);
            }
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
        System.out.println(this.max + "  " + pos + "  " + instancias.get(pos).getVector()[0] + "," +instancias.get(pos).getVector()[1]);
        return pos;*/
        int pos=0;
        int j=0;
        double mayor = 0;
        boolean primerPatron = false;
        //calcular la menor distncia del primer patron a un centroide e ir guardando el mayor de estos
        while(!primerPatron){
            if(!this.posicionesCentroides.contains(j)){
                double menor = distancia(this.instancias.get(j).getVector(), this.centroidesBase.get(0).getVector());
                //System.out.println("MENORRR: " + menor);
                //System.out.println("\t\t"+instancias.get(j).getVector()[0] + "," +instancias.get(j).getVector()[1]);
                
                for(int k=1; k<this.centroidesBase.size(); k++){
                    double aux = distancia(this.instancias.get(j).getVector(), this.centroidesBase.get(k).getVector());
                    if(aux < menor){
                        menor = aux;
                    }
                } primerPatron = true;
                  mayor = menor; 
                  pos = j;
                  j++;
                  //System.out.println(mayor);
            }else j++;
        }
         for(int i=j; i<this.instancias.size(); i++){
            if(!this.posicionesCentroides.contains(i)){
                //cacluclar la distancia entre cada patron y cada centroide, y seleccionar el menor para cada patron
                //de todos estos, elegir el mayot
                double menor = distancia(this.instancias.get(i).getVector(), this.centroidesBase.get(0).getVector());
                for(int k=1; k<this.centroidesBase.size(); k++){
                    double aux = distancia(this.instancias.get(i).getVector(), this.centroidesBase.get(k).getVector());
                    if(aux < menor){
                        menor = aux;
                    }
                }
                //System.out.println(menor);
                if(menor > mayor){
                    mayor = menor;
                    pos = i;
                } 
            }
         }
         this.max=mayor;
         //System.out.println("\n" + mayor);
        return pos;
    }
    
    public void crearNuevoCentroide(int pos){
        int id = this.centroidesBase.size();
        // agregamos el nuevo centroide
        //Patron aux = new Patron(this.instancias.get(pos));
        //aux.setClase("Cluster"+id);
        this.instancias.get(pos).setClaseResultante("Cluster"+(id+1));
        //System.out.println(id);
        this.centroidesBase.add(this.instancias.get(pos));
        this.posicionesCentroides.add(pos);
        // eliminamos la instancia iI
        //this.instancias.remove(pos); //AQUI
        
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
             p.setClaseResultante(this.centroidesBase.get(iMedia).getClaseResultante()); //le da al patron una claseResultante provisional
        }
    }
    
    public Instancias getInstancias(){
        return new Instancias(this.instancias);
    }
    public Instancias getInstanciasC(){
        Instancias i = new Instancias(this.instancias);
        central.setClaseResultante("central");
        i.agregarPatron(central);
        return i;
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
