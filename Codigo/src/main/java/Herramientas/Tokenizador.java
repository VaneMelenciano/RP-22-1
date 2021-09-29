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
import Herramientas.Instancias;

/**
 *
 * @author Vanessa
 */
public class Tokenizador {
    //lee datos de un archivo, los tokeniza (con la clase en la primera posición o el la última), y los guarda en una lista de instancias
    //de tipo Patron, cada instancia tiene su vector y clase original
    
    //private ArrayList<Patron> instancias;
    private Instancias instancias;
    //Leer archivo (csv, txt), tokenizar datos, guardar las instancias (de cada renglon)
    
    private int ordenDeClase; //si la clase se encuentra al final de cada instancia, es 0. Si la clase está al principio, es 1
    
    public Tokenizador(int orden){
        //instancias = new ArrayList<>();
        instancias = new Instancias();
        this.ordenDeClase=orden;
        leerArchivo();
    }
    public Tokenizador(){ //si no recibe el número de orden, toma automaticamente 0, considerando que la clase está al final
        instancias = new Instancias();
        this.ordenDeClase=0;
        leerArchivo();
        //instancias = new ArrayList<>();
    }
    
    //Leer el archivo (csc o txt)
    public void leerArchivo(){
        String aux, texto;
        LinkedList<String> lista = new LinkedList(); //para guardar los datos que se vayan leyendo
        
        
        try {
            JFileChooser file = new JFileChooser(); //llamamos el metodo que permite cargar la ventana
            file.setCurrentDirectory(new File("./"));
            file.showOpenDialog(file);
            //Abre el archivo
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
                
                if(this.ordenDeClase==0){ //la clase está al final de la instancia
                    for (int i = 0; i < lista.size(); i++) { 

                        StringTokenizer tokens = new StringTokenizer(lista.get(i), ","); //va separando los renglones guardado en la lista, por las comas

                        while (tokens.hasMoreTokens()) { //mientras existan tokens (renglones)
                            lista2.add(tokens.nextToken()); //guarda cada dato del renglo en la lista2
                        }

                        double[] vector = new double[lista2.size() - 1]; //declarando un vector para guarda los datos

                        for (int x = 0; x < lista2.size() - 1; x++) { 
                            vector[x] = Double.parseDouble(lista2.get(x)); //convierte cada elemento de la lista2 en double y lo guarda en el vector
                        }

                        clase = lista2.get(lista2.size()-1); //el ultimo de la lista lo toma como la clase
                        instancias.agregarPatron(new Patron(clase, vector)); //agrega el vector como un nuevo patron a las instancias

                        lista2.clear();

                    }
                }else{ //la clase está al principio de la instancia
                    for (int i = 0; i < lista.size(); i++) { 

                        StringTokenizer tokens = new StringTokenizer(lista.get(i), ","); //va separando los renglones guardado en la lista, por las comas

                        while (tokens.hasMoreTokens()) { //mientras existan tokens (renglones)
                            lista2.add(tokens.nextToken()); //guarda cada dato del renglo en la lista2
                        }
                        clase = lista2.get(0); //el primero de la lista lo toma como la clase
                        
                        double[] vector = new double[lista2.size() - 1]; //declarando un vector para guarda los datos

                        for (int x = 1, j=0; x < lista2.size() && j<lista2.size()-1; x++, j++) { 
                            vector[j] = Double.parseDouble(lista2.get(x)); //convierte cada elemento de la lista2 en double y lo guarda en el vector
                        }
                        instancias.agregarPatron(new Patron(clase, vector)); //agrega el vector como un nuevo patron a las instancias

                        lista2.clear();
                    }
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
    public Instancias getInstancias() {
        return instancias;
    }

    /**
     * @param instancias the instancias to set
     */
    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }
    
}
