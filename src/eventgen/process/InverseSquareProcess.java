/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventgen.process;

import eventgen.base.Dimension;
import eventgen.base.IMCProcess;
import eventgen.base.LundEvent;
import eventgen.base.LundHeader;
import eventgen.base.LundParticle;
import eventgen.base.PhaseSpace;
import java.util.Random;

/**
 *
 * @author dmriser
 */
public class InverseSquareProcess implements IMCProcess {

    Random random;
    PhaseSpace phaseSpace;
    Dimension finalEnergy, theta;
    double value, beamEnergy, protonMass, maxCrossSection, totalCrossSection;

    public InverseSquareProcess(double beamEnergy, double thetaMin, double thetaMax, double finalEnergyMin, double finalEnergyMax) {
        random = new Random();
        finalEnergy = new Dimension("eprime", finalEnergyMin, finalEnergyMax);
        theta = new Dimension("theta", thetaMin, thetaMax);

        phaseSpace = new PhaseSpace("Uniform");
        phaseSpace.addDimension(finalEnergy);
        phaseSpace.addDimension(theta);

        this.beamEnergy = beamEnergy;
        value = 0.5;
        protonMass = 0.938;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public LundEvent getEvent(double[] phaseSpacePoint) {

        // phaseSpacePoint[0] = final energy 
        // phaseSpacePoint[1] = theta lab 
        double nu = beamEnergy - phaseSpacePoint[0];
        double q2 = 4 * this.beamEnergy * phaseSpacePoint[0] * Math.pow(Math.sin((3.14159 / 180) * phaseSpacePoint[1] / 2), 2);
        double x = q2 / (2 * protonMass * nu);
        double y = nu / beamEnergy;
        double w = Math.sqrt(Math.pow(protonMass, 2) - q2 + 2 * protonMass * nu);
        double phi = random.nextDouble() * 2 * 3.14159;

        double px = phaseSpacePoint[0] * Math.sin(phaseSpacePoint[1] * (3.14159 / 180)) * Math.cos(phi);
        double py = phaseSpacePoint[0] * Math.sin(phaseSpacePoint[1] * (3.14159 / 180)) * Math.sin(phi);
        double pz = phaseSpacePoint[0] * Math.cos(phaseSpacePoint[1] * (3.14159 / 180));

        LundHeader header = new LundHeader(1, 1, 1, 0.0, 0.0, x, y, w, q2, nu);
        LundParticle electron = new LundParticle(0, -1, 1, 11, 0, 0, px, py, pz, 0.0, 0.0, 0.0, phaseSpacePoint[0], 0.005);
        LundEvent event = new LundEvent();
        event.setHeader(header);
        event.addParticle(electron);

        return event;
    }

    @Override
    public PhaseSpace getPhaseSpace() {
        return phaseSpace;
    }

    @Override
    public double getWeight(double[] phaseSpacePoint) {
        return getValue(phaseSpacePoint) / getMaxCrossSection();
    }

    @Override
    public double getValue(double[] point) {
        return (this.value*Math.pow(point[0], -2.0)*Math.pow(point[1],-2.0));
    }

    @Override
    public double getMaxCrossSection() {
        return this.maxCrossSection;
    }

    @Override
    public double getTotalCrossSection() {
        return this.totalCrossSection;
    }

    @Override
    public void setMaxCrossSection(double maxCrossSection) {
        this.maxCrossSection = maxCrossSection;
    }

    @Override
    public void setTotalCrossSection(double totalCrossSection) {
        this.totalCrossSection = totalCrossSection;
    }

}
