/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import Herramientas.Instancias;
import clasificacionNoSupervisada.Cmeans;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import modelos.Patron;
import modelos.PatronRepresentativo;

/**
 *
 * @author Vanessa
 * para obtener las instancias de la imagen que recibe
 */
public class Adaptador {
    private Image imagen;
    private int alto;
    private int ancho;
    
    public Adaptador(Image imagen){
        this.imagen=imagen;
    }
    public Instancias obtenerInstancia(){
        //transforma cada color de cada pixel, en los vectores de las instancias
        Instancias instancias = new Instancias();
        BufferedImage bimagen = Herramientas.toBufferedImage(this.imagen); //da una matriz de pixeles de la imagen
        //bimagen.getRGB(0, 0);
        this.setAlto(bimagen.getTileHeight());
        this.setAncho(bimagen.getTileWidth());
        for(int i=0; i<ancho; i++){
            for(int j=0; j<alto; j++){
                PixelPatron p = new PixelPatron(new Color(bimagen.getRGB(i, j)), i, j);
                instancias.agregarPatron(p);
            }
        }
        return instancias;
    }
    public Image obtenerImagen(Instancias instancias, ArrayList<PatronRepresentativo> clusters){
        BufferedImage bimagen = new BufferedImage(this.ancho,this.alto, BufferedImage.TYPE_INT_RGB);
        for(Patron aux: instancias.getPatrones()){
            PixelPatron pixel = (PixelPatron)aux;
            
            //Darle el color al pixel que corresponde su clase resutante
            int posicion = Cmeans.buscarClase(aux, clusters); //posicion en los clusters de su clase
            double vector [] = clusters.get(posicion).getVector();

            // nuevo color
            Color nuevoColor = new Color((int)vector[0],(int) vector[1],(int) vector[2]);
            bimagen.setRGB(pixel.getX(),pixel.getY(), nuevoColor.getRGB());
        }
        return Herramientas.toImage(bimagen);
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
