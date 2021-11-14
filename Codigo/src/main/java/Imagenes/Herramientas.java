package Imagenes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vanessa
 */
public class Herramientas {
    
    public static Image lectura(){
         try {
            // definir los fltros de los tipos de imagenes
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes","png","jpg","bmp");
            // declarar e inicializar la venta de seleccion
            JFileChooser seleccion = new JFileChooser();
            seleccion.addChoosableFileFilter(filtro);
            // especificamos que solo puede abrir archivos
            seleccion.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            // ejecutar el selector de archivos
            int resutlado = seleccion.showOpenDialog(null);
            // si el evento fue cancelar
            if (resutlado== JFileChooser.CANCEL_OPTION){
                return null;
            }
            File archivo = seleccion.getSelectedFile();
            BufferedImage bfi = ImageIO.read(archivo);
                      
            return bfi.getScaledInstance(bfi.getWidth(),bfi.getHeight(),BufferedImage.TYPE_INT_RGB);
        } catch (IOException ex) {
            Logger.getLogger(Herramientas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Image toImage(BufferedImage bi){
     
       return bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    }
    
    public static BufferedImage toBufferedImage(Image img){
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
  }
}
