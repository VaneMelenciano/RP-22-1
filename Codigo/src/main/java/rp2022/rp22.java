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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanessa
 */
public class rp22 {
    public static void main(String[] args){
        /*MinimaDistancia mc = new MinimaDistancia();
        
        //ENTRENAMIENTO
        Instancia instancias = new Instancia(); //leer los datos (txt) de los datos para entrenamiento
        mc.entrenar(instancias.getInstancias()); //mandamos los datos para entrenar
        
        //CLASIFICACION
        Instancia instancias2 = new Instancia(); //leer datos (txt) para clasificar
        mc.clasificar(instancias2.getInstancias()); // se clasifican los datos antes leidos
        
        System.out.println(mc.getMc().toString());*/
        
        
        /* Entrenado y clasificando todo con Iris
        K =1: 100%
        K =2: 96%
        K =3: 96%
        K =4: 97%
        K =5: 98%
        K =6: 97%
        */
        KNN knn = new KNN(6);  
        //ENTRENAMIENTO
        Instancia instancias = new Instancia(); //leer los datos (txt) de los datos para entrenamiento
        knn.entrenar(instancias.getInstancias()); //mandamos los datos para entrenar
        
        //CLASIFICACION
        //Instancia instancias2 = new Instancia(); //leer datos (txt) para clasificar
        knn.clasificar(instancias.getInstancias()); // se clasifican los datos antes leidos
        
        System.out.println(knn.getMc().toString());
       
        
    }
}
