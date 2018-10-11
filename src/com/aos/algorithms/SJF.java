package com.aos.algorithms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.aos.utils.Algorithm;
import com.aos.utils.Parameters;
import com.aos.utils.Process;

public class SJF extends Algorithm 
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Queue<Process> schedule(PriorityQueue<Process> q) 
    {
        int end_time = 0;
        int start_time;
        Process p;
        Process p_scheduled;
        Parameters params= this.getParams();
        Queue<Process> scheduler = new LinkedList<>();
 
        PriorityQueue<Process> ready_processes = new PriorityQueue<>(10, 
            new Comparator() 
            {
                @Override
                public int compare(Object o1, Object o2) {
                    Process p1 = (Process) o1;
                    Process p2 = (Process) o2;
                    if (p1.getBurstTime() == p2.getBurstTime())
                        return p1.getArrivalTime() < p2.getArrivalTime() ? -1 : 1;
                    else
                        return p1.getBurstTime() < p2.getBurstTime() ? -1 : 1;
                }            
            });
        
        while (!q.isEmpty() || !ready_processes.isEmpty())
        {            
            p = ready_processes.isEmpty() ? q.poll() : ready_processes.poll();
                       
            start_time = Math.max((int) Math.ceil(p.getArrivalTime()), end_time);
            end_time = start_time + p.getBurstTime();
 
            if (start_time > 100) 
                break;
      
            while (q.peek() != null && q.peek().getArrivalTime() <= end_time)
                ready_processes.add(q.poll());
            
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
