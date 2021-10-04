/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Factor;
import Herramientas.GeneradorInstancias;
import Herramientas.Tokenizador;
import clasesSupervisadas.KNN;
import clasesSupervisadas.MinimaDistancia;
import modelos.Patron;
import modelos.PatronRepresentativo;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanessa
 */
public class rp22 {
    public static void main(String[] args){
        /*//Minima Distancia
        MinimaDistancia mc = new MinimaDistancia();
        
        //ENTRENAMIENTO
        Tokenizador datos = new Tokenizador(); //leer los datos (txt) de los datos para entrenamiento
        mc.entrenar(datos.getInstancias().getPatrones()); //mandamos los datos para entrenar
        
        //CLASIFICACION
        //Instancia instancias2 = new Instancia(1); //leer datos (txt) para clasificar
        mc.clasificar(datos.getInstancias().getPatrones()); // se clasifican los datos antes leidos
        
        System.out.println(mc.getMc().toString());*/
        
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
        
        //Generador de Instancias
        int caracteristicas[] = {0, 1};
        Tokenizador t = new Tokenizador();
        GeneradorInstancias gi = new GeneradorInstancias(t.getInstancias());
        gi.generarInstancia(caracteristicas, 30, Factor.PRIMEROS);
        GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
        gi1.generarInstancia(caracteristicas, 30, Factor.RANDOM);
        GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias());
        gi2.generarInstancia(caracteristicas, 30, Factor.ULTIMOS);
        GeneradorInstancias gi3 = new GeneradorInstancias(t.getInstancias());
        gi3.generarInstancia(caracteristicas, 30, Factor.ENMEDIO);
        System.out.print(gi.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi1.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi2.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi3.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.println();
        
    }
}
