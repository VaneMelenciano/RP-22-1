/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import java.awt.Color;
import modelos.Patron;

/**
 *
 * @author Vanessa
 */
public class PixelPatron extends Patron{
    private int x;
    private int y;
    
    public PixelPatron(String clase, double vector[], int x, int y){
        super(clase, vector);
        this.x=x;
        this.y=y;
    }
    public PixelPatron(double vector[], int x, int y){
        super("", vector);
        this.x=x;
        this.y=y;
    }
    public PixelPatron(Color c, int x, int y){ 
        super("", new double[]{c.getRed(), c.getGreen(), c.getBlue()});
        this.x=x;
        this.y=y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
