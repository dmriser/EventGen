/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventgen.base;

import java.util.Random;

/**
 *
 * @author dmriser
 */
public class Dimension {
    
    double min, max, width;
    String name;

    Random random;
    
    public Dimension(String name, double min, double max) {
        this.min = min;
        this.max = max;
        this.name = name;
        this.width = this.max-this.min;
        random = new Random();
    }
    
    public Dimension(){
        this.name = "Unassigned";
        this.min = 0.0; 
        this.max = 1.0;
        this.width = 1.0;
        random = new Random();
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimension(String name, double min, double max) {
        this.min = min;
        this.max = max;
        this.name = name;
        this.width = this.max - this.min;
    }
    
    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }
 
    public double getRandom(){
        return random.nextDouble()*width +min;
    }
    
}
