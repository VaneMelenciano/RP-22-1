/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2022;

import Herramientas.Factor;
import Herramientas.GeneradorInstancias;
import Herramientas.Grafica;
import Herramientas.Tokenizador;
import Imagenes.ClusterizadorMinMax;
import Imagenes.Herramientas;
import Imagenes.MinMax;
import MemoriasAsociativas.Lernmatrix;
import clasesSupervisadas.KNN;
import clasesSupervisadas.MinimaDistancia;
import clasesSupervisadas.NaiveBayes;
import clasesSupervisadas.PerceptronDelta;
import clasesSupervisadas.RedPerceptron;
import clasificacionNoSupervisada.Cmeans;
import modelos.Patron;
import modelos.PatronRepresentativo;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vanessa
 */
public class rp22 {
    public static void main(String[] args){
        //Minima Distancia
        /*MinimaDistancia mc = new MinimaDistancia();
        
        //ENTRENAMIENTO
        Tokenizador datos = new Tokenizador(); //leer los datos (txt) de los datos para entrenamiento
        mc.entrenar(datos.getInstancias().getPatrones()); //mandamos los datos para entrenar
        
        //CLASIFICACION
        //Instancia instancias2 = new Instancia(1); //leer datos (txt) para clasificar
        mc.clasificar(datos.getInstancias().getPatrones()); // se clasifican los datos antes leidos
        
        System.out.println(mc.getMc().toString());*/
        
        //KNN
        /* Entrenado y clasificando todo con Iris
        K =1: 100%
        K =2: 96.0%
        K =3: 96.66%  
        K =4: 97.33% 
        K =5: 98.0%
        K =6: 97.33%   
        */
        /* Entrenado con 50% - Iris
        K =1: 97.33%
        K =2: 98.0%
        K =3: 98.0%
        K =4: 98.67%
        K =5: 97.33%
        K =6: 97.33% 
        */
        /* Entrenado con 35% - Iris
        K =1: 94.67%
        K =2: 96.67%
        K =3: 95.33%
        K =4: 96.66%
        K =5: 96.0%
        K =6: 95.33%
        */
        //Tokenizador datos = new Tokenizador(1); //leer los datos (txt) de los datos para entrenamiento
        //Tokenizador datos = new Tokenizador(); //leer datos (txt) para clasificar
        /*for(int k=6; k>0; k--){
            System.out.println(ZonedDateTime.now());
            System.out.println("K: " + k);
            KNN knn = new KNN(k);

            //ENTRENAMIENTO
            knn.entrenar(datos.getInstancias().getPatrones()); //mandamos los datos para entrenar
            //CLASIFICACION
            knn.clasificar(datos.getInstancias().getPatrones()); // se clasifican los datos antes leidos
            System.out.println(knn.getMc().toString() + "\n");
        }*/
        System.out.println();
        
        //Generador de Instancias
        /*int caracteristicas[] = {0, 1, 2, 3, 4, 5};
        Tokenizador t = new Tokenizador();
        GeneradorInstancias gi = new GeneradorInstancias(t.getInstancias());
        gi.generarInstancia(caracteristicas, 30, Factor.PRIMEROS);
        GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
        gi1.generarInstancia(caracteristicas, 30, Factor.RANDOM);
        GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias());
        gi2.generarInstancia(caracteristicas, 30, Factor.ULTIMOS);
        GeneradorInstancias gi3 = new GeneradorInstancias(t.getInstancias());
        gi3.generarInstancia(caracteristicas, 30, Factor.ENMEDIO);
        System.out.print(gi.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi1.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi2.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.print(gi3.getNuevasInstancias().getNumPatrones() + "   ");
        System.out.println();*/
        //int c[] = {0};
        

        /*Genra instancias y hace matriz para calcular
            el rendimiento de minima distancia con un DataSet, tonando de
            una en una caracteristicas hasta llegar al numAtributos totales*/
        /*Tokenizador t = new Tokenizador();
        int numAtributos = 8;
        for(int i=1; i<numAtributos; i++){
            int c[] = new int[i+1]; 
            c[0] =0;
            for(int j=1; j<=i; j++){
                c[j] = j;
            }
            for(int k=0; k<=i;k++){
                System.out.print(c[k]);
            }
           System.out.println("\n\t" + (i+1) + " caracteristicas");
           c[i] = i;
           GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
            gi1.generarInstancia(c, 0, Factor.RANDOM);
        
            MinimaDistancia mc = new MinimaDistancia();
            mc.entrenar(gi1.getNuevasInstancias().getPatrones());
            mc.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(mc.getMc().toString() + "\n");
        }*/
            //i=0   c = 1234567
            //i=1   c = 0234567
            //i=2   c = 0134567
            //i=3   c = 0124567
            //i=4   c = 0123567
            //i=5   c = 0123467
            //i=6   c = 0123457
            //i=7   c = 0123456
        /*Genera instancias y hace matriz para calcular
          el rendimiento de minima distancia con un DataSet,
           quitando una caracteristica en cada pasada*/
       //Tokenizador t = new Tokenizador(1);
         /*int numAtributos = 8;
       for(int i=0; i<numAtributos; i++){
            int c[] = new int[7]; 
            for(int j=0, p=0; j<7 && p<8;){
                if(p!=i) {
                    c[j]=p;
                   j++;  
                } p++;
            }
            for(int k=0; k<7;k++){
                System.out.print(c[k]);
            }System.out.println();
           System.out.println("\n\tSin la caracteristica " + i);
           GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
            gi1.generarInstancia(c, 0, Factor.RANDOM);
        
            MinimaDistancia mc = new MinimaDistancia();
            mc.entrenar(gi1.getNuevasInstancias().getPatrones());
            mc.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(mc.getMc().toString() + "\n");
        }*/
       
       /*Genera instancias y hace matriz para calcular
            el rendimiento de KNN con un DataSet, tonando de
            una en una caracteristicas hasta llegar al numAtributos totales*/
        /*Tokenizador t1 = new Tokenizador();
        int numAtributos1 = 8;
        int k=4;
        for(int i=1; i<numAtributos1; i++){
            int c[] = new int[i+1]; 
            c[0] =0;
            for(int j=1; j<=i; j++){
                c[j] = j;
            }
            for(int l=0; l<=i;l++){
                System.out.print(c[l]);
            }
           System.out.println("\n\t" + (i+1) + " caracteristicas");
           c[i] = i;
           GeneradorInstancias gi1 = new GeneradorInstancias(t1.getInstancias());
            gi1.generarInstancia(c, 0, Factor.RANDOM);
        
            KNN knn = new KNN(k);
            knn.entrenar(gi1.getNuevasInstancias().getPatrones());
            knn.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(knn.getMc().toString() + "\n");
        }*/
            //i=0   c = 1234567
            //i=1   c = 0234567
            //i=2   c = 0134567
            //i=3   c = 0124567
            //i=4   c = 0123567
            //i=5   c = 0123467
            //i=6   c = 0123457
            //i=7   c = 0123456
        /*Genera instancias y hace matriz para calcular
          el rendimiento de KNN con un DataSet,
           quitando una caracteristica en cada pasada*/
         /*Tokenizador t = new Tokenizador(1);
         int numAtributos = 8;
         int k=4;
       for(int i=0; i<numAtributos; i++){
            int c[] = new int[7]; 
            for(int j=0, p=0; j<7 && p<8;){
                if(p!=i) {
                    c[j]=p;
                   j++;  
                } p++;
            }
            for(int l=0; l<7;l++){
                System.out.print(c[l]);
            }System.out.println();
           System.out.println("\n\tSin la caracteristica " + i);
           GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
           gi1.generarInstancia(c, 0, Factor.RANDOM);
        
            KNN knn = new KNN(k);
            knn.entrenar(gi1.getNuevasInstancias().getPatrones());
            knn.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(knn.getMc().toString() + "\n");
        }*/
         
         /*int porcentaje[] = {65, 50, 0};
         int con = 0;
         double maxPor1=0;
         double pC=0;
         String max1 = "";
         Tokenizador t = new Tokenizador();
         for(int o=0; o<3; o++){
         double maxPor=0;
         String max = "";*/
       
       /*Genera instancias y hace matriz para calcular
            el rendimiento de NaiveBayes con un DataSet, tomando de
            una en una caracteristicas hasta llegar al numAtributos totales*/
        //Tokenizador t = new Tokenizador(1);
        /*int numAtributos = 32;
        for(int i=1; i<numAtributos; i++){
            int c[] = new int[i+1]; 
            c[0] =0;
            for(int j=1; j<=i; j++){
                c[j] = j;
            }
            //for(int m=0; m<=i;m++){
                //System.out.print(c[m]);
            //}
            String r = (i+1) + " caracteristicas";
           System.out.println("\n\t" + r);
           c[i] = i;
           GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
            gi1.generarInstancia(c, porcentaje[o], Factor.RANDOM);
            
            GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias());
            gi2.generarInstancia(c, 0, Factor.RANDOM);
        
            NaiveBayes nb = new NaiveBayes();
            nb.entrenar(gi1.getNuevasInstancias());
            nb.clasificar(gi2.getNuevasInstancias().getPatrones());
            System.out.println(nb.getMc().toString() + "\n");
            con++;
            if(nb.getMc().calcularEfectividad() > maxPor){
                maxPor = nb.getMc().calcularEfectividad();
                max = r;
            }
        }*/

        /*Genera instancias y hace matriz para calcular
          el rendimiento de  NaiveBayes con un DataSet,
           quitando una caracteristica en cada pasada*/
       //Tokenizador t = new Tokenizador(1);
         //int numAtributos = 8;
       /*for(int i=0; i<numAtributos; i++){
            int c[] = new int[numAtributos-1]; 
            for(int j=0, p=0; j<numAtributos-1 && p<numAtributos;){
                if(p!=i) {
                    c[j]=p;
                   j++;  
                } p++;
            }
            //for(int k=0; k<numAtributos-1;k++){
                //System.out.print(c[k]);
            //}System.out.println();
            String r1 = "Sin la caracteristica " + i;
           System.out.println("\n\t" + r1);
           GeneradorInstancias gi1 = new GeneradorInstancias(t.getInstancias());
            gi1.generarInstancia(c, porcentaje[o], Factor.RANDOM);
        
            GeneradorInstancias gi2 = new GeneradorInstancias(t.getInstancias());
            gi2.generarInstancia(c, 0, Factor.RANDOM);
            
            NaiveBayes nb = new NaiveBayes();
            nb.entrenar(gi1.getNuevasInstancias());
            nb.clasificar(gi2.getNuevasInstancias().getPatrones());
            System.out.println(nb.getMc().toString() + "\n");
            con++;
            if(nb.getMc().calcularEfectividad() > maxPor){
                maxPor = nb.getMc().calcularEfectividad();
                max = r1;
            }
        }
       System.out.println("\nMAXIMO\n" + max + "\t" + maxPor +"\t Con %: " + (100-porcentaje[o]));
         if(maxPor > maxPor1){
             maxPor1=maxPor;
             max1=max;
             pC=100-porcentaje[o];
         }
         }
         
         System.out.println( con);
         System.out.println("\nMAXIMOOOO\n" + max1 + "\t" + maxPor1 +"\t Con %: " + pC);*/
       //Ara??as
        /*Tokenizador t1 = new Tokenizador();
        int numAtributos1 = 8;
        int k=3;
        for(int i=1; i<numAtributos1; i++){
            int c[] = new int[i+1]; 
            c[0] =0;
            for(int j=1; j<=i; j++){
                c[j] = j;
            }
            for(int l=0; l<=i;l++){
                System.out.print(c[l]);
            }
           System.out.println("\n\t" + (i+1) + " caracteristicas");
           c[i] = i;
           GeneradorInstancias gi1 = new GeneradorInstancias(t1.getInstancias());
            gi1.generarInstancia(c, 0, Factor.RANDOM);
            
            System.out.println("\n\t\t" + "Minima Distancia");
            MinimaDistancia mc = new MinimaDistancia();
            mc.entrenar(gi1.getNuevasInstancias().getPatrones());
            mc.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(mc.getMc().toString() + "\n");
            System.out.println("\n\t\t" + "KNN");
            KNN knn = new KNN(k);
            knn.entrenar(gi1.getNuevasInstancias().getPatrones());
            knn.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(knn.getMc().toString() + "\n");
            
        }
       for(int i=0; i<numAtributos1; i++){
            int c[] = new int[7]; 
            for(int j=0, p=0; j<7 && p<8;){
                if(p!=i) {
                    c[j]=p;
                   j++;  
                } p++;
            }
            for(int l=0; l<7;l++){
                System.out.print(c[l]);
            }System.out.println();
           System.out.println("\n\tSin la caracteristica " + i);
           GeneradorInstancias gi1 = new GeneradorInstancias(t1.getInstancias());
            gi1.generarInstancia(c, 0, Factor.RANDOM);
        
           System.out.println("\n\t\t" + "Minima Distancia");
            MinimaDistancia mc = new MinimaDistancia();
            mc.entrenar(gi1.getNuevasInstancias().getPatrones());
            mc.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(mc.getMc().toString() + "\n");
            System.out.println("\n\t\t" + "KNN");
            KNN knn = new KNN(k);
            knn.entrenar(gi1.getNuevasInstancias().getPatrones());
            knn.clasificar(gi1.getNuevasInstancias().getPatrones());
            System.out.println(knn.getMc().toString() + "\n");
        }*/
        
        //NaiveBayes
        /*Tokenizador t1 = new Tokenizador(1);
        NaiveBayes nb = new NaiveBayes();
        nb.entrenar(t1.getInstancias());
        nb.clasificar(t1.getInstancias().getPatrones());
        //nb.imprimirMatriz(nb.getMatrizPromedio());
        //nb.imprimirMatriz(nb.getMatrizVarianza());
        //nb.imprimirMatriz(nb.getDistribucionNormal());
        System.out.println(nb.getMc().toString() + "\n");
        //double m[] = {6, 180, 12};
        //nb.clasificar(new Patron("male", m));*/
        
        
        //Cmeans
        /* Tokenizador t1 = new Tokenizador();
        //ABCD
        //AB, AC, AD, BC, BD, CD
        //A  B  C  D
        //0  1  2  3
        
        int[] c = {1, 3};
        GeneradorInstancias gi = new GeneradorInstancias(t1.getInstancias());
               gi.generarInstancia(c, 0, Factor.RANDOM);
               
        Cmeans cm = new Cmeans(gi.getNuevasInstancias(), 3);
        //Cmeans cm = new Cmeans(t1.getInstancias(), 3);
        cm.clasificar();
        Grafica.graficar(cm.getPatrones(), "CD", "C-means con 3 clusters");*/
       
        /*ArrayList<Patron> patrones = cm.getPatrones();
        for(int i=0; i<patrones.size(); i++){
            Patron patron = patrones.get(i);
            for(Double d : patron.getVector())
                System.out.print(d + "\t");
            //for(int k=0; k<2; k++)
                //System.out.print(patron.getVector()[k] + "\t");
            System.out.println(patron.getClaseResultante());
        }
        System.out.println();*/
        
        
        
        
         //MIN MAX
        Tokenizador t1 = new Tokenizador();
         //generar instancias
         //A  B  C  D
         //0  1  2  3
        int[] c = {1, 2};
        //GeneradorInstancias gi = new GeneradorInstancias(t1.getInstancias());
               //gi1.generarInstancia(c, 0, Factor.RANDOM);
               //double[] umbral = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 1, 1.4, 1.7, 2};
        
        //for(double u: umbral){
            GeneradorInstancias gi1 = new GeneradorInstancias(t1.getInstancias());
               gi1.generarInstancia(c, 0, Factor.RANDOM);
            //Grafica.graficar(gi1.getNuevasInstancias().getPatrones(), "o", "o");
            MinMax mm = new MinMax(gi1.getNuevasInstancias(), 1);
            Grafica.graficar(mm.getInstancias().getPatrones(), "BD", "MinMan con " +"1"+" de umbral");  
            
            /*for(Patron p:mm.getInstanciasC().getPatrones()){
                for(double d:p.getVector()){
                    System.out.print(d + "\t\t");
                } System.out.println(p.getClaseResultante());
            }*/
            /*for(Patron p: mm.getcentroidesBase()){
                for(double d:p.getVector()){
                    System.out.print(d + "\t\t");
                } System.out.println(p.getClaseResultante());
            }*/
        //}
        
        //double[] v = {0.1, 0.2, 0.3, 0.5, 0.8, 1, 3, 4, 5};
        //ClusterizadorMinMax cm = new ClusterizadorMinMax(v);
        
        
        //PATRON DELTA
        /*ArrayList<Patron> patrones = new ArrayList<Patron>();
        patrones.add(new Patron("0", new double[]{0,0}));
        patrones.add(new Patron("0", new double[]{0,1}));
        patrones.add(new Patron("0", new double[]{1,0}));
           patrones.add(new Patron("1", new double[]{1,1}));*/
        /*
         AND      OR       XOR
        0 0 0    0 0 0    0 0 0
        0 1 0    0 1 1    0 1 1
        1 0 0    1 0 1    1 0 1
        1 1 1    1 1 1    1 1 0
        */
        //AND NO = 1.5, 1.5, -3, 5
        /*double[] pesos = {1.5, 2.5};
        double alpha = -2;//0.02;
        double umbral = 5;//7.1;
        PerceptronDelta p = new PerceptronDelta(pesos, 0.02, 7.1, patrones);*/
        /*
        Pruebas:
        double[] pesos = {0.5, 0.6};
        double alpha = 0.02;
        double umbral = 7.1;
        */
        /*System.out.println();
        int a = 0;
        System.out.println("x1\t" + "x2\t" + "y'\t" + "y");
        for(Patron pa: p.getInstancias()){
            for(int i=0; i<2; i++){
                a = (int)pa.getVector()[i];
                System.out.print(a + "\t");
            }
            System.out.println(pa.getClase() + "\t" + pa.getClaseResultante());
        }*/
        
        //PATRON MULTICAPA
        /*RedPerceptron p = new RedPerceptron();
       
        double input[][] = new double[][]{{0,0},{0,1},{1,0},{1,1}};
        double t[][] = new double[][]{{0},{0},{0},{1}}; //AND
        //double t[][] = new double[][]{{0},{1},{1},{1}}; //OR
        //double t[][] = new double[][]{{0},{1},{1},{0}}; //XOR
        
        p.entrenar(input, t);
        p.clasifica(new double[]{0,0});
        p.clasifica(new double[]{0,1});
        p.clasifica(new double[]{1,0});
        p.clasifica(new double[]{1,1});
        System.out.println();
        System.out.println("x1\t" + "x2\t" + "y'\t" + "y");
        int i=0;
        for(double[] d: input){
            System.out.print((int)d[0]+"\t"+(int)d[1]+"\t");
            System.out.print((int)t[i][0] + "\t" + (int)t[i++][0] +"\n");
        
        }*/
        /*int n =  69;//916;
        char m = (char)n;
        System.out.println(m);*/
        /*int[][] m = new int[3][4];
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                m[i][j]= j;
                System.out.print(m[i][j]);
            }
            System.out.println();
        }*/
        /*Tokenizador t = new Tokenizador();
        Tokenizador t1 = new Tokenizador();
        Lernmatrix l = new Lernmatrix(t.getInstancias().getPatrones(), t1.getInstancias().getPatrones());
        l.imprimirMatriz();
        //Patron p = new Patron(new double[] {1,0,1,0,1});
        //Patron p = new Patron(new double[] {1,1,0,0,1});
        Patron p = new Patron(new double[] {1,0,1,1,0});
        l.recuperacion(p);
        System.out.println();*/
        /*int[] v = l.recuperacion(p);
        for(int i=0; i<v.length; i++){
           System.out.println(v[i]);
        }*/
    }
}

