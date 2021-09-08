/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Instancias;
import clasesSupervisadas.MinimaDistancia;
import data.Patron;
import data.PatronRepresentativo;
import java.util.ArrayList;

/**
 *
 * @author Vanessa
 */
public class rp22 {
    public static void main(String[] args){
        /*Patron p1 = new Patron("", new double []{2,1,4.5,6.7});
        Patron p2 = new Patron("", new double []{3,10,4.5,9.7});
        Patron p3 = new Patron("", new double []{11,12,4.5,6.7});
        Patron p4 = new Patron("", new double []{5,6.7,1.5,5.7});
    
        PatronRepresentativo pr = new PatronRepresentativo("", new double[] {0,0,0,0});
        pr.acumular(p1);
        pr.acumular(p2);
        pr.acumular(p3);
        pr.acumular(p4);
        pr.promediar();
        System.out.println();*/
        Instancias.leerArchivo();
        MinimaDistancia minD = new MinimaDistancia();
        minD.entrenar(Instancias.instancias);
        ArrayList<Patron> patrones = new ArrayList<Patron>();
        Patron p1 = new Patron(new double []{5,9,1,0});
        Patron p2 = new Patron(new double []{3,8,7,6});
        Patron p3 = new Patron(new double []{1,2,3,4});
        Patron p4 = new Patron(new double []{6,7,0,1});
        Patron p5 = new Patron(new double []{3,4,3,2});
        patrones.add(p1);
        patrones.add(p2);
        patrones.add(p3);
        patrones.add(p4);
        patrones.add(p5);
        minD.clasificar(patrones);
        System.out.println();
        
    }
}
