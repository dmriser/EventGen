/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventgen.base;

import java.util.ArrayList;

/**
 *
 * @author dmriser
 */
public class PhaseSpace {

    ArrayList<Dimension> dimensions;
    String name;

    public PhaseSpace(String name) {
        this.name = name;
        dimensions = new ArrayList<Dimension>();
    }

    public PhaseSpace() {
        dimensions = new ArrayList<Dimension>();

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Dimension getDimension(int index) {
        if (index < getDimensionality()) {
            return dimensions.get(index);
        } else {
            return new Dimension();
        }
    }

    public void addDimension(Dimension dim){
        dimensions.add(dim);
    }
    
    public int getDimensionality() {
        return dimensions.size();
    }

    public double[] getRandom(){
        
        double[] point = new double[getDimensionality()];
        for(int dim=0; dim<getDimensionality(); dim++){
            point[dim] = dimensions.get(dim).getRandom();
        }
        
        return point;
    }
    
    public double getPhaseSpaceVolume(){
        double volume = 1;
        for (int dim=0; dim<getDimensionality(); dim++){
            volume *= dimensions.get(dim).getWidth();
        }
        return volume; 
    }
    
}
