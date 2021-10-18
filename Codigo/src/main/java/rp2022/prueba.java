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
import clasesSupervisadas.NaiveBayes;
import modelos.Patron;
import modelos.PatronRepresentativo;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Vanessa
 */
public class prueba {
    public static void main(String[] args){
        //NaiveBayes
        Tokenizador t = new Tokenizador(1);
        NaiveBayes nb = new NaiveBayes();
        int c[] = {0, 2, 3, 4, 5, 6, 7};
        GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
           gi1.generarInstancia(c, 0, Factor.RANDOM);
        
            KNN knn = new KNN(4);
            knn.entrenar(gi1.getNuevasInstancias().getPatrones());
            knn.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(knn.getMc().toString() + "\n");
        //double m[] = {6, 180, 12};
        //nb.clasificar(new Patron("male", m));
    }
}
