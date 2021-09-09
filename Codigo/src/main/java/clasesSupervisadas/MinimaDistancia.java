/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesSupervisadas;

import data.Patron;
import data.PatronRepresentativo;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */

//Clasificación supervisada
/*
2 pasos principales:
    ENTRENAMIENTO
        - Obtener los datos (patrones) representativos de las clases involucradas
        - Generar un conjunto de patrones representativos de cada uno de las clases (media)
    CLASIFICACION
        - Recibir el o el conjunto de patrones "desconocidos" a clasificar
        - Utilizando el patron P se calcula la distancia con respeto a los patrones 
            representativos de cada una de las clases, la distancia más corta define 
            la clase a la que pertenece el patron O "desconocido"
*/
public class MinimaDistancia {
    
    private ArrayList<PatronRepresentativo> representativos;
    private MatrizConfusion mc;
    
    public MinimaDistancia(){
        this.representativos = new ArrayList<PatronRepresentativo>();
        
    }
    
    //ENTRENAMIENTO
    public void entrenar(ArrayList<Patron> instancias){
        // generar los representativos
        //agregar el primer representativo, calculado del primer elemento de las instancias
        representativos.add(new PatronRepresentativo(instancias.get(0))); 
        
        
        for (int x=1; x<instancias.size();x++){ //iniciando el 1, el 0 se agrego arriba
            Patron aux = instancias.get(x);
            
           boolean encontrado = false; //bandera para saber si exite la clase representativa de la nueva instancia
           int posicion = 0;
           for(int i=0; i<representativos.size(); i++){ //recorre los representativos
               if(representativos.get(i).getClase().equals(aux.getClase())){ //compara la clase de la nueva instancia con la clase de los representativos
                   encontrado=true;
                   posicion=i;
                   break;
               }
           }
           
            if(encontrado){
                //si exite agrega ese elemento como uno representativo
                representativos.get(posicion).acumular(aux);
            }
            else{
                //si no existe, crea un representativo (para una nueva clase)
                representativos.add(new PatronRepresentativo(aux));
            }
        }
        
        for(PatronRepresentativo aux: representativos){ //calcula el promedio de los representativos
            aux.promediar();
        }  
    }
    
    //CLASIFICAR
    public void clasificar(Patron p){
        //- Recibir el patron "desconocidos" a clasificar = p
        //calcular la distancia con los patrones representativos
        double minDistancia = distancia(p.getVector(), representativos.get(0).getVector());
        int iRepresentativo = 0; //posicion del patron representativo donde corresponde el nuevo patron
        //calcular la distancia entre el vector del nuevo patron con los representativos
        for(int y=1; y<representativos.size(); y++){
            double disAux = distancia(p.getVector(), representativos.get(y).getVector());
            if(minDistancia > disAux){
                minDistancia = disAux;
                iRepresentativo = y;
            }
        }
        //agregar el nuevo patron a la clase donde exista menos distancia entre sus vectores, y volver a calcular el promedio con la nueva instancia acumulada
        p.setClaseResultante(representativos.get(iRepresentativo).getClase()); //le da el nombre de su "ClaseResultante" al patron que se calificó
        
    }
    
    public void clasificar(ArrayList<Patron> patrones) { //En caso de recibir una lista de patrones, va clasificando uno por uno
       for(Patron p: patrones){
           clasificar(p);
       }
       
        mc = new MatrizConfusion(); //se mandan los patrones ya clasificados para crear la matriz de confusion
        mc.construirMatriz(patrones);
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

