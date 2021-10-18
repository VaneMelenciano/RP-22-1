/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesSupervisadas;

import Herramientas.Instancias;
import Herramientas.MatrizConfusion;
import java.util.ArrayList;
import modelos.Patron;

/**
 *
 * @author Vanessa
 */
public class NaiveBayes {
    private MatrizConfusion mc; //para crear la matriz de confusión después de clasificar
    private Instancias instancias; //recibidas para entrenar
    private double[][] matrizPromedio;
    private double[][] matrizVarianza;
    private double[][] matrizDesvicion;
    private double[][] distribucionNormal;
    private double[] pClase; //probabilidad por clase //apriori
    private double[] pClaseCal; //probabilidad por clase calculado en la clasificación //posteriori
   
    public NaiveBayes(){
        
    }
    public void entrenar(Instancias instancias){
        //1.	Se recibe el dataset
        this.instancias=instancias; //recibe instancias
        this.matrizPromedio=new double[this.instancias.getNumClases()][this.instancias.getNumAtributos()];
        this.matrizVarianza=new double[this.instancias.getNumClases()][this.instancias.getNumAtributos()];
        this.matrizDesvicion=new double[this.instancias.getNumClases()][this.instancias.getNumAtributos()];
        this.pClase= new double[this.instancias.getNumClases()];
        //2.	Vector promedio
            //suma de todas la caracterisicas de la clase y1 para atributo x1 
            for(Patron patron: this.instancias.getPatrones()){
                 int posClase =this.instancias.getClases().indexOf(patron.getClase()); //buscar la poscion de su clase en clases que será columna en el vetor promedio
                 for(int i=0; i<patron.getVector().length; i++){
                     this.matrizPromedio[posClase][i] += patron.getVector()[i];
                 } 
            }
           // imprimirMatriz(this.matrizPromedio);
            //divide entre el numero de instancias por cada clase
            for(int i=0; i<this.instancias.getNumClases(); i++){
                int sizeClase = this.instancias.getCantidadPorClase().get(i);
                for(int j=0; j<this.instancias.getNumAtributos(); j++){
                   this.matrizPromedio[i][j] /= sizeClase;
                }
            }
        //3.	Vectores varianza
        for(Patron patron: this.instancias.getPatrones()){
            int posClase =this.instancias.getClases().indexOf(patron.getClase()); //buscar la poscion de su clase
            for(int i=0; i<patron.getVector().length; i++){
     
                     this.matrizVarianza[posClase][i] += (Math.pow(patron.getVector()[i] - this.matrizPromedio[posClase][i], 2));
            }
        }
        //divide entre el numero de instancias por cada clase menos uno
        for(int i=0; i<this.instancias.getNumClases(); i++){
                int sizeClase = this.instancias.getCantidadPorClase().get(i) -1;
                for(int j=0; j<this.instancias.getNumAtributos(); j++){
                   this.matrizVarianza[i][j] /= sizeClase;
                }
            }
        //4.	Vector desviación
        for(int i=0; i<this.instancias.getNumClases(); i++){
                int sizeClase = this.instancias.getCantidadPorClase().get(i) -1;
                for(int j=0; j<this.instancias.getNumAtributos(); j++){
                  this.matrizDesvicion[i][j] = Math.sqrt(this.matrizVarianza[i][j]);
                }
            }
        //Probabilidad por clase
        for(int i=0; i<this.pClase.length; i++){ //apriori
            this.pClase[i]= (double)this.instancias.getCantidadPorClase().get(i)/this.instancias.getNumPatrones();
            
        }
    }
    
    public void clasificar(Patron patron){
        this.distribucionNormal=new double[this.instancias.getNumClases()][this.instancias.getNumAtributos()];
        this.pClaseCal= new double[this.instancias.getNumClases()];
        double evidencia = 0;
        
        //Matriz de distribución normal
        for(int i=0; i<this.distribucionNormal.length; i++){
            double aux = this.pClase[i];
            for(int j=0; j<this.instancias.getNumAtributos(); j++){
                double varianza = this.matrizVarianza[i][j];
                double promedio = this.matrizPromedio[i][j];
                this.distribucionNormal[i][j] = (1/ Math.sqrt(2*Math.PI*varianza)) * Math.exp((-1 * Math.pow((patron.getVector()[j]-promedio), 2))/(2*varianza));
                //this.distribucionNormal[i][j] = (1/ Math.sqrt(2*Math.PI*this.matrizVarianza[i][j])) * Math.exp((-1 * Math.pow((patron.getVector()[j]-this.matrizPromedio[i][j]), 2))/(2*this.matrizVarianza[i][j]));
                aux*=this.distribucionNormal[i][j];
            } evidencia+=aux; 
        }
        
        
        //Porcentaje por cada clase con la matrix de distribución normal
        int posClase=-1; //posicion de la clase resultante
        double porcentaje = -1; //porcentaje resultante de la clase resultante (maximo porcentaje)
        for(int i=0; i<this.pClaseCal.length; i++){ //posteriori
            this.pClaseCal[i]=this.pClase[i];
            for(int j=0; j<this.instancias.getNumAtributos(); j++){
                this.pClaseCal[i] *= this.distribucionNormal[i][j]; 
            }
            this.pClaseCal[i]/=evidencia;
            if(this.pClaseCal[i]>porcentaje){
                porcentaje = this.pClaseCal[i];
                posClase=i;
            }
        }
        patron.setClaseResultante(this.instancias.getClases().get(posClase));
    }
    public void clasificar(ArrayList<Patron> patrones) { //En caso de recibir una lista de patrones, va clasificando uno por uno
       for(Patron p: patrones){
           clasificar(p);
       }
       
        mc = new MatrizConfusion(); //se mandan los patrones ya clasificados para crear la matriz de confusion
        mc.construirMatriz(patrones);
       //los patrones que mandamos, tiene ya su clase resultante y clase original
    }
    
    public double[][] getDistribucionNormal() {
        return this.distribucionNormal;
    }
    public double[][] getMatrizPromedio() {
        return this.matrizPromedio;
    }
    public double[][] getMatrizVarianza() {
        return this.matrizVarianza;
    }
    public MatrizConfusion getMc() {
        return mc;
    }
    public void imprimirMatriz(double[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
               System.out.print(matriz[i][j] + " ");
            }  System.out.println();
        } System.out.println();
    }
}
