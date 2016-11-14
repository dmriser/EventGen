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
public class LundHeader {

    int numberOfParticles, numberOfTargetNucleons, numberOfTargetProtons;
    double beamPolarization, targetPolarization, x, y, w, qq, nu;

    public LundHeader() {
        this.numberOfParticles = 0;
        this.numberOfTargetNucleons = 0;
        this.numberOfTargetProtons = 0;
        this.beamPolarization = 0.0;
        this.targetPolarization = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.w = 0.0;
        this.qq = 0.0;
        this.nu = 0.0;
    }

    public LundHeader(int numberOfParticles, int numberOfTargetNucleons, int numberOfTargetProtons, 
            double beamPolarization, double targetPolarization, double x, double y, double w, double qq, double nu) {
        this.numberOfParticles = numberOfParticles;
        this.numberOfTargetNucleons = numberOfTargetNucleons;
        this.numberOfTargetProtons = numberOfTargetProtons;
        this.beamPolarization = beamPolarization;
        this.targetPolarization = targetPolarization;
        this.x = x;
        this.y = y;
        this.w = w;
        this.qq = qq;
        this.nu = nu;
    }

    public int getNumberOfParticles() {
        return numberOfParticles;
    }

    public void setNumberOfParticles(int numberOfParticles) {
        this.numberOfParticles = numberOfParticles;
    }

    public int getNumberOfTargetNucleons() {
        return numberOfTargetNucleons;
    }

    public void setNumberOfTargetNucleons(int numberOfTargetNucleons) {
        this.numberOfTargetNucleons = numberOfTargetNucleons;
    }

    public int getNumberOfTargetProtons() {
        return numberOfTargetProtons;
    }

    public void setNumberOfTargetProtons(int numberOfTargetProtons) {
        this.numberOfTargetProtons = numberOfTargetProtons;
    }

    public double getBeamPolarization() {
        return beamPolarization;
    }

    public void setBeamPolarization(double beamPolarization) {
        this.beamPolarization = beamPolarization;
    }

    public double getTargetPolarization() {
        return targetPolarization;
    }

    public void setTargetPolarization(double targetPolarization) {
        this.targetPolarization = targetPolarization;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getQq() {
        return qq;
    }

    public void setQq(double qq) {
        this.qq = qq;
    }

    public double getNu() {
        return nu;
    }

    public void setNu(double nu) {
        this.nu = nu;
    }

    @Override
    public String toString() {
        return String.format("%1$d %2$4d %3$4d %4$8f %5$8f %6$8f %7$08f %8$08f %9$08f %10$08f \n",
                numberOfParticles, numberOfTargetNucleons, numberOfTargetProtons, 
            beamPolarization, targetPolarization, x, y, w, qq, nu);
    }
    
}
