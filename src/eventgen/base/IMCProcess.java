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
public interface IMCProcess {
    LundEvent getEvent(double[] phaseSpacePoint);
    PhaseSpace getPhaseSpace();
    double getWeight(double[] phaseSpacePoint);
    double getValue(double[] point);
    double getMaxCrossSection();
    double getTotalCrossSection();
    void setMaxCrossSection(double maxCrossSection);
    void setTotalCrossSection(double totalCrossSection);
}
