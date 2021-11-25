/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.util.ArrayList;
import modelos.Patron;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.PublicCloneable;
/**
 *
 * @author Vanessa
 */
public class Grafica {
    private JFreeChart grafica;
    private XYSeriesCollection series;
    private String ejeX, ejeY,titulo, titulo1;

    public Grafica(String ejeX, String ejeY, String titulo, String titulo1) {
        this.grafica = null;
        this.series = new XYSeriesCollection();
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.titulo = titulo;
        this.titulo1=titulo1;
    }
    public void agrearSerie(String nombre){
        XYSeries serie = new XYSeries(nombre);
        this.series.addSeries(serie);
    }
    public void agregarDatoASerie(String nombre, XYDataItem dato){
       this.series.getSeries(nombre).add(dato);
    }
    public void agregarSerie(String nombre, double[] datos){
    
        XYSeries serie = new XYSeries(nombre);
        // agregar cada uno de los datos en la serie 
        for (int x=0; x < datos.length;x++){
            serie.add(x, datos[x]);
        }
        // agregamos la serie que se generó 
        this.series.addSeries(serie);
     
    }
    
    public void crearYmostrarGrafica(){
    
        //this.grafica = ChartFactory.createXYLineChart(titulo, ejeX, ejeY, series);
        this.grafica = ChartFactory.createScatterPlot(this.titulo, ejeX, ejeX, series);
        ChartFrame frame = new ChartFrame(titulo1, grafica);
        frame.setVisible(true);
        
        
    }
     public static void graficar(ArrayList<Patron> instancias, String tituloGrafica, String titulo){
        // 0,1,2,3
        // recorrer las instancias y verificar los datos para crear las series
        ArrayList<String> clusters = new ArrayList<>();
        Grafica grafica = new Grafica("ejex", "ejeY", tituloGrafica, titulo);
        clusters.add(instancias.get(0).getClaseResultante());
        grafica.agrearSerie(""+instancias.get(0).getClaseResultante());
        for(Patron aux: instancias){
            // verificar si ya existe en instancias un clase igual
         if(!clusters.contains(aux.getClaseResultante())){
             // se agrega
             clusters.add(aux.getClaseResultante());
             grafica.agrearSerie(""+aux.getClaseResultante());
         }
        }
        // agregar los datos a la serie con las clases involucradas
        for(Patron aux: instancias){
            double datos [] = aux.getVector();
            XYDataItem dato = new XYDataItem(datos[0], datos[1]);
            grafica.agregarDatoASerie(aux.getClaseResultante(),dato);
        }
        // generar la grafica

        grafica.crearYmostrarGrafica();
    }
    
}
