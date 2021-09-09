/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ //https://www.youtube.com/watch?v=ttgV5kfiH7Y

package Herramientas;

import data.Patron;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Vanessa
 */
public class Instancias {
    private ArrayList<Patron> instancias;
    //Leer archivo (csv, txt), tokenizar datos, guardar las instancias (de cada renglon)
    
    public Instancias(){
        leerArchivo();
        //instancias = new ArrayList<>();
    }
    
    //Leer el archivo (csc o txt)
    public void leerArchivo(){
        instancias = new ArrayList<Patron>();
        String aux, texto;
        LinkedList<String> lista = new LinkedList(); //para guardar los datos que se vayan leyendo
        
        
        try {
            JFileChooser file = new JFileChooser(); //indicar el archivo que vamos a abrir
            file.setCurrentDirectory(new File("./"));
            file.showOpenDialog(file);
            //Leer el archivo abierto
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) { //verifica que esté abierto
                
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto = aux; //guarda la linea del archivo leido en el String
                    lista.add(texto); //añade el String anterior a la lista
                }
                lee.close();
                
                
                //TOKENIZAR DATOS
                ArrayList<String> lista2 = new ArrayList<>(); //un renglon
                String clase = "";
                
                for (int i = 0; i < lista.size(); i++) { 
                    
                    StringTokenizer tokens = new StringTokenizer(lista.get(i), ","); //va separando los renglones guardado en la lista, por las comas

                    while (tokens.hasMoreTokens()) { //mientras existan tokens (renglones)
                        lista2.add(tokens.nextToken()); //guarda cada dato del renglo en la lista2
                    }

                    double[] vector = new double[lista2.size() - 1]; //declarando un vector para guarda los datos

                    for (int x = 0; x < lista2.size() - 1; x++) { //750
                        vector[x] = Double.parseDouble(lista2.get(x)); //convierte cada elemento de la lista2 en double y lo guarda en el vector
                    }

                    clase = lista2.get(lista2.size()-1); //el ultimo de la lista lo toma como la clase
                    instancias.add(new Patron(clase, vector)); //agrega el vector como un nuevo patron a las instancias
                    
                    lista2.clear();

                }
          
            }
            
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nArchivo no encontrado",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
    }

    /**
     * @return the instancias
     */
    public ArrayList<Patron> getInstancias() {
        return instancias;
    }

    /**
     * @param instancias the instancias to set
     */
    public void setInstancias(ArrayList<Patron> instancias) {
        this.instancias = instancias;
    }
    
}
