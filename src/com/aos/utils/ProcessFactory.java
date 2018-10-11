package com.aos.utils;

import java.util.PriorityQueue;
import java.util.Random;


public class ProcessFactory
{

    public static PriorityQueue<Process> generateProcesses(int process_count)
    {
        
        PriorityQueue<Process> process_queue = new PriorityQueue<>();

        Random arrival_rand = new Random();
        Random priority_rand = new Random();        
        Random exptime_rand = new Random();
        
        double arrival_next = 0.0;

        // Generate new processes and add to the process queue 
        for(int i = 0; i != process_count && arrival_next < 95; ++i)
        {		
            Process p = new Process();
            p.setArrivalTime(arrival_next); 
            p.setBurstTime(exptime_rand.nextInt(10) + 1);
            p.setPriority(priority_rand.nextInt(4) + 1);
            p.setName("P" + i);
            process_queue.add(p);
            
            arrival_next += arrival_rand.nextFloat() * 10;
        }
        return process_queue;
    }
}
