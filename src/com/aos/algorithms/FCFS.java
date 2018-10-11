package com.aos.algorithms;

import java.util.Queue;

import com.aos.utils.Algorithm;
import com.aos.utils.Parameters;

import java.util.LinkedList;
import java.util.PriorityQueue;
import com.aos.utils.Process;

public class FCFS extends Algorithm
{
    @Override
    public Queue<Process> schedule(PriorityQueue<Process> q) 
    {
        int start_time = 0;
        int p_length = q.size();
        int end_time = 0;
        Process p;
        Process p_scheduled;
        Parameters params = this.getParams();
        Queue<Process> scheduler = new LinkedList<>();
        
        for (int i = 0; i < p_length; ++i)
        {
            p = q.poll();
            
            start_time = Math.max((int) Math.ceil(p.getArrivalTime()), end_time);            
            end_time = start_time + p.getBurstTime();

            if (start_time > 100) 
                break;

            params.incrementWaitTime(start_time - p.getArrivalTime());
            params.incrementTurnAroundTime(end_time - p.getArrivalTime());
            params.incrementResponseTime(end_time - start_time);
            params.incrementProcess();                      

            p_scheduled = new Process();
            p_scheduled.setBurstTime(p.getBurstTime());
            p_scheduled.setStartTime(start_time);
            p_scheduled.setName(p.getName());
            scheduler.add(p_scheduled);            
        }
        params.incrementQuanta(end_time);
        calculateAvgParamsForEachRun();
        params.reset();
        
        return scheduler;
    }
}
