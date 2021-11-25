/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import clasificacionNoSupervisada.Cmeans;
import Imagenes.MinMax;
import java.awt.Image;

/**
 *
 * @author Vanessa
 */
public class ClusterizadorMinMax {
     private Image imagenOriginal;
     private Image nuevaImagen;
     private int umbral;
     
    public ClusterizadorMinMax(int n){
        this.umbral=n;
         abrirImagen();
         clasificar();
    }
    public ClusterizadorMinMax(int[] n){
        abrirImagen(); 
        for(int i=0; i<n.length; i++){
          this.umbral = n[i];
          clasificar();
        }
    }

    public void abrirImagen() {
        this.imagenOriginal = Herramientas.lectura(); //abrir imagen
        JframeImagen frame = new JframeImagen(imagenOriginal); //mostar imagen
        frame.setTitle("Imagen Orignal");
    }
    public void clasificar(){
       Adaptador ad = new Adaptador(this.imagenOriginal);
       MinMax mm = new MinMax(ad.obtenerInstancia(), 2);
       
       this.nuevaImagen = ad.obtenerImagen(mm.getInstancias(), mm.getcentroidesBase1());
       JframeImagen frame = new JframeImagen(this.nuevaImagen); //mostar imagen
       frame.setTitle("Imagen Nueva con " + this.umbral + " de umbral");
    } 
}
