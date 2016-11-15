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
public class MultiProcessGenerator {
    
    ArrayList<SingleProcessGenerator> generators;
    
    public void MultiProcessGenerator(){
    
    }
    
    public SingleProcessGenerator getGenerator(int index){
        if (index < getNumberOfProcesses()){
            return generators.get(index);
        }
    
        System.out.println("[MultiProcessGenerator] Cannot get generator index=" + index);
        return new SingleProcessGenerator();
    }
    
    public void addProcess(IMCProcess process){
        SingleProcessGenerator gen = new SingleProcessGenerator();
        gen.setProcess(process);
        generators.add(gen);
    }
    
    public int getNumberOfProcesses(){
        return generators.size();
    }


}
