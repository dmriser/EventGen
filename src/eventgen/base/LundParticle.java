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
public class LundParticle {

    int index, charge, type, PID, parentIndex, daughterIndex;
    double px, py, pz, vx, vy, vz, energy, mass;

    public LundParticle() {
        this.index = 0;
        this.charge = 0;
        this.type = 1; 
        this.PID = 0; 
        this.parentIndex = 0; 
        this.daughterIndex = 0;
        this.px = 0.0;
        this.py = 0.0; 
        this.pz = 0.0; 
        this.vx = 0.0; 
        this.vy = 0.0;
        this.vz = 0.0;
        this.energy = 0.0;
        this.mass = 0.0;
    }

    public LundParticle(int index, int charge, int type, int PID, int parentIndex, 
            int daughterIndex, double px, double py, double pz, double vx, double vy, double vz, 
            double energy, double mass) {
        this.index = index;
        this.charge = charge;
        this.type = type;
        this.PID = PID;
        this.parentIndex = parentIndex;
        this.daughterIndex = daughterIndex;
        this.px = px;
        this.py = py;
        this.pz = pz;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.energy = energy;
        this.mass = mass;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public int getDaughterIndex() {
        return daughterIndex;
    }

    public void setDaughterIndex(int daughterIndex) {
        this.daughterIndex = daughterIndex;
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public double getPz() {
        return pz;
    }

    public void setPz(double pz) {
        this.pz = pz;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setMomentum(double px, double py, double pz){
        this.px = px; 
        this.py = py;
        this.pz = pz; 
    }
    
    public void setVertex(double vx, double vy, double vz){
        this.vx = vx; 
        this.vy = vy; 
        this.vz = vz;
    }

    @Override
    public String toString() {
        return String.format("%1$d %2$4d %3$4d %4$4d %5$4d %6$4d %7$08f %8$08f %9$08f %10$08f %11$08f %12$08f %13$08f %14$08f \n",
                index, charge, type, PID, parentIndex, daughterIndex, px, py, pz, vx, vy, vz, energy, mass);
    }    
    
}
