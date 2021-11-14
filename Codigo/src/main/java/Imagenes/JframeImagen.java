/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vanessa
 */

public class JframeImagen extends JFrame{

    private Image imagenOriginal;

    public JframeImagen(Image imagenOriginal){                                    
        this.imagenOriginal = imagenOriginal;
        JLabel etiqueta = new JLabel(new ImageIcon(this.imagenOriginal));
        setLayout(new GridLayout(1,1));
        add(etiqueta);
        setSize(new Dimension(this.imagenOriginal.getWidth(null), this.imagenOriginal.getWidth(null)));
        setVisible(rootPaneCheckingEnabled);
    }
    
    public Image getImagenOriginal(){
        return this.imagenOriginal;
    }
    public void setImagenOriginal(Image aux){
        this.imagenOriginal = aux;
    }


}