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

    H1F[] randomParameters;
    H1F[] acceptedParameters;

    public SingleProcessGenerator() {
        events = new ArrayList<LundEvent>();
        integrator = new MCIntegrationUniform();
        random = new Random();
    }

    public void setProcess(IMCProcess process) {
        this.process = process;
        initializeParameterHistograms();
    }

    private void initializeParameterHistograms() {
        randomParameters = new H1F[process.getPhaseSpace().getDimensionality()];
        acceptedParameters = new H1F[process.getPhaseSpace().getDimensionality()];

        for (int dim = 0; dim < process.getPhaseSpace().getDimensionality(); dim++) {
            Dimension currentDimension = process.getPhaseSpace().getDimension(dim);
            randomParameters[dim] = new H1F(currentDimension.getName(), 100, currentDimension.getMin(), currentDimension.getMax());
            acceptedParameters[dim] = new H1F(currentDimension.getName(), 100, currentDimension.getMin(), currentDimension.getMax());
        }
    }

    public H1F[] getParameterHistograms(int type) {
        if (type == 0) {
            return randomParameters;
        }
        if (type == 1) {
            return acceptedParameters;
        }

        System.out.println("[SingleProcessGenerator] Error getting parameter histograms");
        return null;
    }

    public void addEvent(LundEvent event) {
        events.add(event);

    }

    public H1F getHistogram(String xVar, int numberBins, double min, double max) {
        H1F histo = new H1F(xVar, numberBins, min, max);

        for (LundEvent ev : events) {
            histo.fill(ev.get(xVar));
        }

        return histo;
    }

    public H2F getHistogram(String xVar, String yVar, int numberBinsX, double minX, double maxX,
            int numberBinsY, double minY, double maxY) {

        H2F histo = new H2F(String.format("%sVs%s", yVar, xVar), numberBinsX, minX, maxX, numberBinsY, minY, maxY);

        for (LundEvent ev : events) {
            histo.fill(ev.get(xVar), ev.get(yVar));
        }

        return histo;
    }

    public H2F getHistogram(int pid, String xVar, String yVar, int numberBinsX, double minX, double maxX,
            int numberBinsY, double minY, double maxY) {

        H2F histo = new H2F(String.format("%sVs%sPID", yVar, xVar, pid), numberBinsX, minX, maxX, numberBinsY, minY, maxY);

        for (LundEvent ev : events) {
            for (int particle = 0; particle < ev.getNumberOfParticles(); particle++) {
                if (ev.getParticle(particle).getPID() == pid) {
                    histo.fill(ev.get(xVar, particle), ev.get(yVar, particle));
                }
            }
        }

        return histo;
    }

    public H1F getHistogram(int pid, String xVar, int numberBins, double min, double max) {
        H1F histo = new H1F(xVar, numberBins, min, max);

        for (LundEvent ev : events) {
            for (int particle = 0; particle < ev.getNumberOfParticles(); particle++) {
                if (ev.getParticle(particle).getPID() == pid) {
                    histo.fill(ev.get(xVar, particle));
                }
            }
        }

        return histo;
    }

    public void generate(int numberOfEvents) {

        integrator.integrate(process);
        process.setMaxCrossSection(integrator.getMaximum());
        process.setTotalCrossSection(integrator.getIntegral());

        int eventsGenerated = 0;
        double[] point = new double[process.getPhaseSpace().getDimensionality()];

        while (events.size() < numberOfEvents) {
            point = process.getPhaseSpace().getRandom();
            saveParametersToHistogram(point);

            if (eventIsAccepted(point)) {
                addEvent(process.getEvent(point));
                saveAcceptedParametersToHistogram(point);
            }
            eventsGenerated++;
        }

        System.out.println("Successfully generated " + events.size() + " events in " + eventsGenerated + " tries!");
    }

    private void saveAcceptedParametersToHistogram(double[] point) {
        for (int dim = 0; dim < process.getPhaseSpace().getDimensionality(); dim++) {
            acceptedParameters[dim].fill(point[dim]);
        }
    }

    private void saveParametersToHistogram(double[] point) {
        for (int dim = 0; dim < process.getPhaseSpace().getDimensionality(); dim++) {
            randomParameters[dim].fill(point[dim]);
        }
    }

    private boolean eventIsAccepted(double[] point) {
        return process.getWeight(point) > random.nextDouble();
    }

    public LundEvent getEvent(int index) {
        if (index < events.size()) {
            return events.get(index);
        }

        return new LundEvent();
    }

    public ArrayList<LundEvent> getEvents(){
        return this.events; 
    }
    
    public void saveEvents(String filename) {
        try {
            PrintWriter out = new PrintWriter(filename, "UTF-8");
            for (LundEvent ev : events) {
                out.print(ev.toString());
            }
            out.close();
        } catch (Exception e) {
            System.out.println("[SingleProcessGenerator] Failed to save events to file " + filename);
        }

    }

}
