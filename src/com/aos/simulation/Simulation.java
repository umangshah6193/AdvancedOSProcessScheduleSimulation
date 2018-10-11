package com.aos.simulation;

import java.util.PriorityQueue;
import com.aos.utils.Algorithm;
import com.aos.algorithms.*;
import com.aos.utils.Process;
import com.aos.utils.ProcessFactory;
import com.aos.utils.SimulationConstants;

public class Simulation
{
    public static void main(String[] args) throws Exception {
        Algorithm firstComeFirstServe = new FCFS();
        Algorithm shortestJobFirst = new SJF();
        Algorithm roundRobin = new RR();
        Algorithm shortestRunTime = new SRT();
        Algorithm highestPriorityFirstNonPrem = new HPF_NP();
        Algorithm highestPriorityFirstPrem = new HPF_P();

        PriorityQueue<Process>[] q = new PriorityQueue[SimulationConstants.ALGOS + 1];
        q = (PriorityQueue<Process>[]) q;
        
        for (int i = 0; i < SimulationConstants.TOTAL_SIMULATIONS; i++) {
            System.out.println("################################# Simulation Number "+ (i + 1));
            
            q[0] = ProcessFactory.generateProcesses(SimulationConstants.TOTAL_PROCESS);
            for (int j = 1; j < SimulationConstants.ALGOS + 1; ++j)
                q[j] = SimulationConstants.copyQueue(q[0]);
  
            while (!q[SimulationConstants.ALGOS].isEmpty())
                System.out.println(q[SimulationConstants.ALGOS].poll());

            System.out.println("\n============================== First Come First Serve ==============================");
            firstComeFirstServe.schedule(q[0]);
            
            System.out.println("\n============================== Shortest Job First ==============================");
            shortestJobFirst.schedule(q[1]);
            
            System.out.println("\n============================== Round Robin ==============================");
            roundRobin.schedule(q[2]); 
            
            System.out.println("\n============================== Shortest Remaining Time ==============================");
            shortestRunTime.schedule(q[3]);
                        
            System.out.println("\n============================== Non-Preemptive Highest Priority First ==============================");
            highestPriorityFirstNonPrem.schedule(q[4]);
            
            System.out.println("\n============================== Preemptive Highest Priority First ==============================");
            highestPriorityFirstPrem.schedule(q[5]);
                       
        }
        System.out.println("###################################################### Overall Average Algorithms ######################################################");
        
        System.out.println("\n============================== First Come First Serve ==============================");
        firstComeFirstServe.calculateAllAverage();

        System.out.println("\n============================== Shortest Job First ==============================");
        shortestJobFirst.calculateAllAverage();
        
        System.out.println("\n============================== Round Robin ==============================");
        roundRobin.calculateAllAverage();

        System.out.println("\n============================== Shortest Remaining Time ==============================");
        shortestRunTime.calculateAllAverage();

        System.out.println("\n============================== Non-Preemptive Highest Priority First ==============================");
        highestPriorityFirstNonPrem.calculateAllAverage();
        
        System.out.println("\n============================== Preemptive Highest Priority First ==============================");
        highestPriorityFirstPrem.calculateAllAverage();
    }
}
