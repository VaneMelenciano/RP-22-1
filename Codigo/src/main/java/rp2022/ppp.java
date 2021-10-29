/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Tokenizador;
import clasesSupervisadas.NaiveBayes;

/**
 *
 * @author Vanessa
 */
public class ppp {
    public static void main(String[] args){
        

        Tokenizador t = new Tokenizador();
      
        NaiveBayes nb = new NaiveBayes();
        
        nb.entrenar(t.getInstancias());
        nb.clasificar(t.getInstancias().getPatrones());
        System.out.println(nb.getMc().toString()+"\n");
    }
    
}
