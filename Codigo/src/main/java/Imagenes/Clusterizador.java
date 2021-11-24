/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;


import clasificacionNoSupervisada.Cmeans;
import java.awt.Image;

/**
 *
 * @author Vanessa
 */
/*
    //abrir imagen
    //tomar j pixeles diferentes de la imagen al azar que seran los custers
    //extraer el tono de los j pixeles
    //con base a los j clusters, clasificar nos nxm pixeles de la imagen, en una nueva imagen
*/
public class Clusterizador {    //abrir imagen
     private int numClusters;
     private Image imagenOriginal;
     private Image nuevaImagen;
     
    public Clusterizador(int n){
         this.numClusters = n;
         abrirImagen();
         clasificar();
    }
    public Clusterizador(int[] n){
        abrirImagen(); 
        for(int i=0; i<n.length; i++){
          this.numClusters = n[i];
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
       Cmeans cm = new Cmeans(ad.obtenerInstancia(), this.numClusters);
       cm.clasificar();
       this.nuevaImagen = ad.obtenerImagen(cm.getInstancias(), cm.getMedias());
       JframeImagen frame = new JframeImagen(this.nuevaImagen); //mostar imagen
       frame.setTitle("Imagen Nueva con " + this.numClusters + " clusters");
    }
}
