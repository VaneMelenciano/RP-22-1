/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoriasAsociativas;

import java.util.ArrayList;
import modelos.Patron;

/**
 *
 * @author Vanessa
 */
public class Lernmatrix {
    private ArrayList<Patron> patrones;
    private ArrayList<Patron> asociaciones;
    private int longA, longP;
    public int[][] matriz;
    public Lernmatrix(ArrayList<Patron> p, ArrayList<Patron> a){
        this.asociaciones = a;
        this.patrones = p;
        //this.matriz = new int[this.asociaciones.size()][this.patrones.size()];
        this.longA =this.asociaciones.get(0).getVector().length;
        this.longP =this.patrones.get(0).getVector().length;
        this.matriz = new int[this.longA][this.longP];
        aprendizaje();
    }
    public void aprendizaje(){
        int longitud = this.asociaciones.size(), m=0;
        while(m<longitud){
            Patron a = this.asociaciones.get(m);
            
            for(int i=0; i<this.longA; i++){
                if(a.getVector()[i]==0){
                    for(int j=0; j<this.longP; j++)
                        this.matriz[i][j]=0+this.matriz[i][j];
                }else{ //1
                    Patron p = this.patrones.get(m);
                    for(int j=0; j<this.longP; j++){
                         if(p.getVector()[j]==0)
                             this.matriz[i][j]=-1;
                         else
                             this.matriz[i][j]=1;
                    }
                }
            }
            
            m++;
        }
    }
     public void  recuperacion(ArrayList<Patron> patrones){
         for(Patron p: patrones){
            recuperacion(p); 
         }
     }
    public void  recuperacion(Patron p){
        int[] vectorR = new int[this.matriz.length]; //vectorRecuperacion
        double[] vector = p.getVector();
        imprimir(vector);
        for(int i=0; i<this.matriz.length; i++){
            for(int j=0; j<this.matriz[0].length; j++)
                vectorR[i]+= (int) (this.matriz[i][j]*vector[j]);
        }
        System.out.println("   =");
        imprimir(vectorR);
        //saber el mayor valor del vector
        int[] maxPos = new int[vectorR.length];
        int[] max=new int[vectorR.length]; //se encontra el mayor del vector
        int k=0;
        maxPos[k]=k; max[k]=vectorR[k];
        //boolean mas=false; //si hay más de un valor con el mayor vector
        for(int i=1; i<vectorR.length; i++){
            if(vectorR[i]>max[k]){
                max[k]=vectorR[i]; maxPos[k] = i;
            }else if(vectorR[i]==max[k] && vectorR[i]>1){
                k++;
                max[k]=vectorR[i]; maxPos[k] = i;
            }
        }
        //imprimir(max);
        //imprimir(maxPos);
        //llenar el vector de recuperación con 0 y 1
        int j=0;
        for(int i=0; i<vectorR.length; i++){
            if(i==maxPos[j]){
                vectorR[i] = 1; j++;
            }
            else vectorR[i] = 0;
        }
        System.out.println("   =");
        imprimir(vectorR);
        p.setClaseResultante(String.valueOf(clasificar(vectorR)));
        
    }
    public int clasificar(int[] v){
        boolean es = false;
        int pos = -1;
        for(int i=0; i<v.length;){ //asociaciones
            double[] a = this.asociaciones.get(i).getVector();
            //System.out.println("i: " + i);
            for(int j=0; j<v.length;){ //vectorR
                //System.out.println("\nj: " + j);
                if(v[j]==a[j]){
                    //System.out.println("\tj: " + j + "  " + v[j] + "  " + a[j]);
                    j++;
                    es = true;
                    pos=i;
                    
                }else{
                    //System.out.println("NOO");
                    i++; j=0;
                    es = false;
                    break;
                }
            }
            if(es) break;
        }
        return pos+1;
    }
    
    public void imprimirMatriz(){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        //matriz
        System.out.println("   Matriz");
        for(int i=0; i<filas; i++){
            System.out.print("|");
            for(int j=0; j<columnas; j++){
                if(matriz[i][j]<=0)
                    System.out.print("-E" + " ");
                else
                    System.out.print("E" + " ");
            }  System.out.println("|");
        } System.out.println("        \nx\n");
    }
    
    public void imprimir(int[] v){
        for(int i=0; i<v.length; i++){
           System.out.println(v[i]);
        }System.out.println();
    }
    public void imprimir(double[] v){
        for(int i=0; i<v.length; i++){
           System.out.println((int)v[i]);
        }System.out.println();
    }
}
