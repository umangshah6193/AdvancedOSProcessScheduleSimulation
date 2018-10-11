package com.aos.utils;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SimulationConstants {
    public static final int TOTAL_SIMULATIONS = 5;
    public static final int TOTAL_PROCESS = 20;
    public static final int ALGOS = 6;
    
    public static PriorityQueue<Process> copyQueue(PriorityQueue<Process> q) throws CloneNotSupportedException
    {        
        PriorityQueue<Process> qcopy = new PriorityQueue<>();
        ArrayList<Process> qoriginal = new ArrayList<>();
        while (!q.isEmpty())
        {
            Process p = q.poll();
            qcopy.add((Process) p.clone());
            qoriginal.add(p);
        }
        q.addAll(qoriginal);
        return qcopy;
    }
}
