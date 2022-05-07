/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesSupervisadas;
//https://s3.amazonaws.com/heatonresearch-books/free/encog-3_3-quickstart.pdf pag 15
//https://s3.amazonaws.com/heatonresearch-books/free/Encog3Java-User.pdf pag 44

import java.util.ArrayList;
import modelos.Patron;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

/**
 *
 * @author Vanessa
 */
public class RedPerceptron {
     private BasicNetwork red;
     private ArrayList<Integer> resultados;

    public RedPerceptron() {
        
        // construir la red 
        this.red = new BasicNetwork();
        this.red.addLayer(new BasicLayer(null, true, 2));
        this.red.addLayer(new BasicLayer(new ActivationSigmoid(), true, 3));
        this.red.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));
        this.red.getStructure().finalizeStructure();
        this.red.reset();
        this.resultados = new ArrayList<Integer>();
    }
    
    public void entrenar(double input[][], double t[][]){
        MLDataSet trainingSet = new BasicMLDataSet(input, t);
        // se genera el entrenamiento
        ResilientPropagation train = new ResilientPropagation(this.red,trainingSet);
        int epoca = 0;
        do{
        train.iteration();
        System.out.println("Epoca#: "+epoca+" Error: "+train.getError());
        epoca++;
        }while(train.getError()>0.1);
        //}while(train.getError()>0.26);
        
    }
   public void clasifica(double x[]){
       BasicMLData dato = new BasicMLData(x);
       MLData y = this.red.compute(dato);
       //System.out.println("y= "+y.getData(0));
       System.out.println("y= "+Math.round(y.getData(0)));
       this.resultados.add((int)Math.round(y.getData(0)));
   
   }
   public ArrayList<Integer> getInstancias(){
        return this.resultados;
    }
}
