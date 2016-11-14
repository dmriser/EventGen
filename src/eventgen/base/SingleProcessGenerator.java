/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventgen.base;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;

/**
 *
 * @author dmriser
 */
public class SingleProcessGenerator {
 
    ArrayList<LundEvent> events;
    MCIntegrationUniform integrator;
    IMCProcess process;
    Random random;
    
    H1F px, py, energy;
    H2F pxpy;
    
    public SingleProcessGenerator() {
        events = new ArrayList<LundEvent>();
        integrator = new MCIntegrationUniform();
        random = new Random();
        
        px = new H1F("px",100,-2,2);
        py = new H1F("py",100,-2,2); 
        energy = new H1F("energy",100,0,10);
        pxpy = new H2F("pxpy",100,-2,2,100,-2,2);
    }

    public void setProcess(IMCProcess process) {
        this.process = process;
    }
    
    public void addEvent(LundEvent event){
        events.add(event);
        px.fill(event.getParticle(0).getPx());
        py.fill(event.getParticle(0).getPy());
        energy.fill(event.getParticle(0).getEnergy());
        pxpy.fill(event.getParticle(0).getPx(), event.getParticle(0).getPy());
    }
    
    public H1F getHistogram(String title){
        if (title == "px"){ return px; }
        if (title == "py"){ return py; }
        if (title == "energy"){ return energy; }
        return new H1F();
    }
    
    public H2F getHistogram(String titleX, String titleY){
        if (titleX == "px" && titleY == "py"){ return pxpy; }
        return new H2F();
    }
    
    public void generate(int numberOfEvents){
        
        integrator.integrate(process);
        process.setMaxCrossSection(integrator.getMaximum());
        process.setTotalCrossSection(integrator.getIntegral());
           
        int counter = 0;
        double[] point = new double[process.getPhaseSpace().getDimensionality()];
        while(events.size() < numberOfEvents){
            point = process.getPhaseSpace().getRandom();
            double currentRand = random.nextDouble();
            double weight = process.getWeight(point);
            if (process.getWeight(point) > random.nextDouble()){ addEvent(process.getEvent(point)); }                                   
            counter++;
        }
        
        
        System.out.println("Successfully generated " + events.size() + " events in " + counter + " tries!");
    }

    public LundEvent getEvent(int index){
        if (index < events.size()){
            return events.get(index);
        }
        
        return new LundEvent();
    }
    
    public void saveEvents(String filename) {
        try {
            PrintWriter out = new PrintWriter(filename, "UTF-8");
            for (LundEvent ev : events) {
                out.print(ev.toString());
            }
            out.close();
        } catch (Exception e){
            System.out.println("[SingleProcessGenerator] Failed to save events to file " + filename);
        }

    }
    
}
