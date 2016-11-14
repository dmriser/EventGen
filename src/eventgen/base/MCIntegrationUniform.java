/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventgen.base;

/**
 *
 * @author dmriser
 */
public class MCIntegrationUniform implements IMCIntegration{

    int numberOfSamples; 
    double lastValue, lastMax, lastError;

    public MCIntegrationUniform(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
        this.lastValue = 0.0;
        this.lastMax = 0.0; 
        this.lastError = 0.0;
    }

    public MCIntegrationUniform() {
        this.numberOfSamples = 1000000;
        this.lastValue = 0.0;
        this.lastMax = 0.0; 
        this.lastError = 0.0;
    }
    
    public void integrate(IMCProcess process){
        PhaseSpace phaseSpace = process.getPhaseSpace();
        double[] point = new double[phaseSpace.getDimensionality()];
    
        double total = 0.0; 
        double max = 0.0; 
        double error = 0.0;
        
        for (int sample=0; sample<numberOfSamples; sample++){
            point = phaseSpace.getRandom();
            double value = process.getValue(point);
            total += value; 
            
            if (value > max){ max = value; }
        }
    
        total *= (phaseSpace.getPhaseSpaceVolume()/numberOfSamples);
        lastValue = total; 
        lastMax = max; 
        lastError = error;
    }
    
    public void integrate(IMCProcess process, int numberOfSamples){
        this.numberOfSamples = numberOfSamples; 
        integrate(process);
    }
    
    @Override
    public double getIntegral() {
        return lastValue; 
    }

    @Override
    public double getMaximum() {
        return lastMax;
    }

    @Override
    public double getError() {
        return lastError;
    }
    
}
