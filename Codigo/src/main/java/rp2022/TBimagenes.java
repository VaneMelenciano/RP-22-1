/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Imagenes.Clusterizador;
import Imagenes.Herramientas;
import Imagenes.JframeImagen;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Vanessa
 */
public class TBimagenes {
    public static void main(String[] args){
        //Clusterizador c = new Clusterizador(600);
        int[] num = {2, 5, 15};
        //int[] num = {1, 3, 5, 8, 15};
        Clusterizador c = new Clusterizador(num);
        //c.abrir();
        /*Image imagen = Herramientas.lectura();
        JframeImagen jf = new JframeImagen(imagen);
        
        BufferedImage aux = Herramientas.toBufferedImage(imagen); //da una matriz de pixeles de la imagen
        Color verde = new Color(191, 255, 0);
        Color verde2 = new Color(57, 255, 20);
        for(int i=100; i<200; i++){
            for(int j=100; j<300; j++){
                aux.setRGB(i, j, verde.getRGB());
            }
        }      
        Image imagenNueva = Herramientas.toImage(aux);
        JframeImagen jf1 = new JframeImagen(imagenNueva);*/
    }
}
