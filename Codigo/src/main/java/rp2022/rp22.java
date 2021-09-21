/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Instancia;
import clasesSupervisadas.KNN;
import clasesSupervisadas.MinimaDistancia;
import data.Patron;
import data.PatronRepresentativo;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanessa
 */
public class rp22 {
    public static void main(String[] args){
        //Minima Distancia
        MinimaDistancia mc = new MinimaDistancia();
        
        //ENTRENAMIENTO
        Instancia instancias = new Instancia(1); //leer los datos (txt) de los datos para entrenamiento
        mc.entrenar(instancias.getInstancias()); //mandamos los datos para entrenar
        
        //CLASIFICACION
        //Instancia instancias2 = new Instancia(1); //leer datos (txt) para clasificar
        mc.clasificar(instancias.getInstancias()); // se clasifican los datos antes leidos
        
        System.out.println(mc.getMc().toString());
        
        //KNN
        /* Entrenado y clasificando todo con Iris
        K =1: 100%
        K =2: 96.0%
        K =3: 96.66%  
        K =4: 97.33% 
        K =5: 98.0%
        K =6: 97.33%   
        */
        /* Entrenado con 50% - Iris
        K =1: 97.33%
        K =2: 98.0%
        K =3: 98.0%
        K =4: 98.67%
        K =5: 97.33%
        K =6: 97.33% 
        */
        /* Entrenado con 35% - Iris
        K =1: 94.67%
        K =2: 96.67%
        K =3: 95.33%
        K =4: 96.66%
        K =5: 96.0%
        K =6: 95.33%
        */
        /*Instancia instancias = new Instancia(1); //leer los datos (txt) de los datos para entrenamiento
        //Instancia instancias2 = new Instancia(); //leer datos (txt) para clasificar
        for(int k=6; k>0; k--){
            System.out.println(ZonedDateTime.now());
            System.out.println("K: " + k);
            KNN knn = new KNN(k);

            //ENTRENAMIENTO
            knn.entrenar(instancias.getInstancias()); //mandamos los datos para entrenar
            //CLASIFICACION
            knn.clasificar(instancias.getInstancias()); // se clasifican los datos antes leidos
            System.out.println(knn.getMc().toString() + "\n");
        }
        System.out.println();*/
    }
}
