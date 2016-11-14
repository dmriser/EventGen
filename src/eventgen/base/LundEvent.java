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
public class LundEvent {

    LundHeader header;
    ArrayList<LundParticle> particles;

    public LundEvent(LundHeader header, ArrayList<LundParticle> particles) {
        this.header = header;
        this.particles = particles;
    }

    public LundEvent() {
        header = new LundHeader();
        particles = new ArrayList<LundParticle>();
    }

    public void setHeader(LundHeader header) {
        this.header = header;
    }

    public void addParticle(LundParticle particle) {
        particles.add(particle);
    }

    public LundParticle getParticle(int index) {
        if (index < header.numberOfParticles) {
            return particles.get(index);
        }

        System.out.println("[LundParticle] Particle requested out of bounds, returning new LundParticle.");
        return new LundParticle();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(header.toString());

        for (int p = 0; p < getNumberOfParticles(); p++) {
            builder.append(particles.get(p).toString());
        }

        return builder.toString();
    }

    public int getNumberOfParticles() {
        return particles.size();
    }

    double get(String xVar) {
        if (xVar == "x") {
            return header.getX();
        }
        if (xVar == "y") {
            return header.getY();
        }
        if (xVar == "qq") {
            return header.getQq();
        }
        if (xVar == "w") {
            return header.getW();
        }
        if (xVar == "nu") {
            return header.getNu();
        }

        System.out.println("[LundEvent] Error trying to get " + xVar + " returning 0!");
        return 0.0;
    }

    double get(String xVar, int index) {
        if (index < header.getNumberOfParticles()) {
            if (xVar == "px") {
                return particles.get(index).getPx();
            }
            if (xVar == "py") {
                return particles.get(index).getPy();
            }
            if (xVar == "pz") {
                return particles.get(index).getPz();
            }
            if (xVar == "vx") {
                return particles.get(index).getVx();
            }
            if (xVar == "vy") {
                return particles.get(index).getVy();
            }
            if (xVar == "vz") {
                return particles.get(index).getVz();
            }
            if (xVar == "energy") {
                return particles.get(index).getEnergy();
            }
        }

        System.out.println("[LundEvent] Error trying to get " + xVar + " returning 0!");
        return 0.0;
    }

}
