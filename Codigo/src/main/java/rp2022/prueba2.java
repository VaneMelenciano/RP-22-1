/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Factor;
import Herramientas.GeneradorInstancias;
import Herramientas.Tokenizador;
import clasesSupervisadas.NaiveBayes;

/**
 *
 * @author Vanessa
 */
public class prueba2 {
    public static void main(String[] args){
     //NaiveBayes
        /*Tokenizador t1 = new Tokenizador();
        NaiveBayes nb = new NaiveBayes();
        nb.entrenar(t1.getInstancias());
        Tokenizador t11 = new Tokenizador();
        nb.clasificar(t11.getInstancias().getPatrones());
        //nb.imprimirMatriz(nb.getMatrizPromedio());
        //nb.imprimirMatriz(nb.getMatrizVarianza());
        //nb.imprimirMatriz(nb.getDistribucionNormal());
        System.out.println(nb.getMc().toString() + "\n");*/
        //double m[] = {6, 180, 12};
        //nb.clasificar(new Patron("male", m));
        /*
        100= 72.68664563617244%
        50= Efectividad : 72.10304942166141%
        35 = Efectividad : 71.79284963196635%
        */
        int porcentaje[] = {65, 50, 0}; //para tener 35%, 50% y 100% de los datos
         Tokenizador t = new Tokenizador(); //lee el archivo
        int numAtributos = t.getInstancias().getNumAtributos();
        int w=7;
        while(w>0){
        
        double resultados[][] = new double[(numAtributos*2)][3];
        String descripcion[] = new String[(numAtributos*2)-1];
        int d = -1; //contador para llenar la descripción
        int l=0; //filas de la matriz resultado
        double[] maxPorcentajes = new double[3];
        String[] maxDescripcion = new String[3];
        for(int o=0; o<3; o++){ //recorre las filas}
            System.out.println("o: " + o);
                maxPorcentajes[o] = 0;
                l=0;
           for(int i=1; i<numAtributos; i++){ //recorre las columas
               System.out.println("i: " + i);
               int c[] = new int[i+1]; //atributos que se quieren
         
               c[0] =0; //siempre se toma el primero
               for(int j=1; j<=i; j++){ //primero toma 2, luego 3, luego 4.... hasta numAtributos
                   c[j] = j;
               }
               for(int kk: c) System.out.print(kk+ " ");
              String r = (i+1) + " ca.     "; //dice cuantas caracteristicas se tomaron

              if(o==0){ //pone la descripcion solo en la primera columna de cada fila
                  d++;
                  descripcion[d] = r;
              }

              c[i] = i;
                GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias()); //para entrenar
               gi1.generarInstancia(c, porcentaje[o], Factor.RANDOM);

               GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias()); //para clasificar
               gi2.generarInstancia(c, 0, Factor.RANDOM);

               NaiveBayes nb = new NaiveBayes();
               nb.entrenar(gi1.getNuevasInstancias());
               nb.clasificar(gi2.getNuevasInstancias().getPatrones());
               System.out.println(nb.getMc().toString() + "\n");
               resultados[l][o]=nb.getMc().calcularEfectividad(); //pone la efectividad en la matriz
               if(resultados[l][o] > maxPorcentajes[o]){ //para saber el maximo porcentaje
                   maxPorcentajes[o] = resultados[l][o];
                   maxDescripcion[o] = r;
               }
               l++; //salta a la sig fila de la matriz de resultados
            }
            for(int i=0; i<numAtributos; i++){ //recorre las columas
               int c[] = new int[numAtributos-1]; //atributos que se quieren
               for(int j=0, p=0; j<numAtributos-1 && p<numAtributos;){
                   if(p!=i) { //va agregarndo cada numero al arreglo menos i, que es el que se quiere evitar
                       c[j]=p;
                      j++;  
                   } p++;
               }
               /*
               1234
               0234
               0134
               0123
               */
               String r1 = "Sin ca. " + (i+1) + " ";
              if(o==0){//pone la descripcion solo en la primera columna de cada fila
                  d++;
                  descripcion[d] = r1;
              }
              GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias()); //para entrenar
               gi1.generarInstancia(c, porcentaje[o], Factor.RANDOM);

               GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias()); //para clasificar
               gi2.generarInstancia(c, 0, Factor.RANDOM);

               NaiveBayes nb = new NaiveBayes();
               nb.entrenar(gi1.getNuevasInstancias());
               nb.clasificar(gi2.getNuevasInstancias().getPatrones());

               resultados[l][o]=nb.getMc().calcularEfectividad(); //pone la efectividad en la matriz
               System.out.println(nb.getMc().toString() + "\n");
               if(resultados[l][o] > maxPorcentajes[o]){ //para saber el maximo porcentaje
                   maxPorcentajes[o] = resultados[l][o];
                   maxDescripcion[o] = r1;
               }
               
               l++; //salta a la sig fila de la matriz de resultados
            }
        }
         
        int filas = resultados.length-1;
        int columnas = resultados[0].length;

        System.out.print("\t\t\t\tIris\n"); //titulo de la bd
        System.out.println("             \t\t  Entrenando con");
        System.out.println("             " + "\t\t35%\t\t  \t50%\t\t    \t100%");
        //System.out.println("             " + "\t 35%\t\t  50%\t\t 100%");
        for(int i=0; i<filas; i++){
            System.out.print(descripcion[i]);
            if((i>=0 && i<=7) || (i>=12))System.out.print(" ");
            for(int j=0; j<columnas; j++){
               //System.out.print("\t " + Math.round(resultados[i][j]*100.0)/100.0 + " ");
               System.out.print("\t " + resultados[i][j] + " ");
            }  System.out.println();
        } System.out.println();
        System.out.println("Maximos porcentajes para cada entrenamiento");
        for(int i=0; i<3; i++){
            System.out.println((100-porcentaje[i]) + "%: maximo: " +  maxPorcentajes[i] + " cuando " + maxDescripcion[i]);
        }
        w--;}
    }
}
