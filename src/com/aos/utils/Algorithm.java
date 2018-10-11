package com.aos.utils;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Algorithm 
{
    private Parameters params = new Parameters();
    public abstract Queue<Process> schedule(PriorityQueue<Process> q);
    
    public Parameters getParams() {
    	return this.params;
    }
    
    public void calculateAllAverage()
    {
    	System.out.println("Turnaround time |\t\t Waiting time |\t\t Response Time |\t\t Throughput ");
    	System.out.println(" "+ params.getAvgTurnaroundTime() + "\t\t" + params.getAvgWaitingTime() + "\t\t" + params.getAvgResponseTime() + "\t\t" + params.getAvgThroughput());
    }

    public void calculateAvgParamsForEachRun()
    {
    	System.out.println("Turnaround time |\t\t Waiting time |\t\t Response Time |\t\t Throughput ");
    	System.out.println(" "+  params.getAvgTurnaroundTimeForRun() + "\t\t" + params.getAvgWaitingTimeForRun() + "\t\t" + params.getAvgResponseTimeForRun() + "\t\t" + params.getAvgThroughputForRun());
    }
}
