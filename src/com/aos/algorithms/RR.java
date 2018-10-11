package com.aos.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aos.utils.Algorithm;
import com.aos.utils.Parameters;
import com.aos.utils.Process;

public class RR extends Algorithm 
{    
    @Override
    public Queue<Process> schedule(PriorityQueue<Process> q) 
    {
        int start_time, end_time = 0;
        Process p, p_scheduled, left_out;
        Parameters params= this.getParams();
           
        Map<String, Integer> start_times = new HashMap<>();        
        Map<String, Integer> end_times = new HashMap<>();
        
        Queue<Process> ready_processes = new LinkedList<>();        
        Queue<Process> waiting_processes = new LinkedList<>();
        Queue<Process> scheduler = new LinkedList<>();     
        
        while (!q.isEmpty() || !ready_processes.isEmpty() || !waiting_processes.isEmpty())
        {
            while (!q.isEmpty() && q.peek().getArrivalTime() <= end_time)
                ready_processes.add(q.poll());
            
            if (!ready_processes.isEmpty())
                p = ready_processes.poll();
            else if (!q.isEmpty() && waiting_processes.isEmpty())
                p = q.poll();
            else
                p = waiting_processes.poll(); 

            start_time = Math.max((int) Math.ceil(p.getArrivalTime()), end_time);
            end_time = start_time + 1;
      
            if (!start_times.containsKey(p.getName()))
            {
                if (start_time > 100)
                    break;
                start_times.put(p.getName(), start_time);
                params.incrementWaitTime(start_time - p.getArrivalTime());
                params.incrementResponseTime(start_time - p.getArrivalTime() + 1);
            }
            else
                params.incrementWaitTime(start_time - end_times.get(p.getName()));
 
            if (p.getBurstTime() > 1)
            {
                try 
                {
                    left_out = (Process) p.clone();
                    left_out.setBurstTime(left_out.getBurstTime() - 1);
                    waiting_processes.add(left_out);
                    end_times.put(left_out.getName(), end_time);
                } 
                catch (CloneNotSupportedException ex) 
                {
                    Logger.getLogger(HPF_NP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                params.incrementTurnAroundTime(end_time - start_times.get(p.getName()));
                params.incrementProcess();
            }            
            
            p_scheduled = new Process();
            p_scheduled.setBurstTime(1);
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
