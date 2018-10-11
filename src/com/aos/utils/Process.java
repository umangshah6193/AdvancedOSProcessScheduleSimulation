package com.aos.utils;


@SuppressWarnings("rawtypes")
public class Process implements Cloneable, Comparable
{   
	private String name;
    private double arrival_time; 
    private int priority;   	
    private int burst_time;     	
    private int start_time;      

    public double getArrivalTime() { 
    	return arrival_time; 
    }
    
    public void setArrivalTime(double arrival_time) { 
    	this.arrival_time = arrival_time; 
    }
    
    public int getBurstTime() { 
    	return burst_time; 
    }
    
    public void setBurstTime(int burst_time) { 
    	this.burst_time = burst_time; 
    }
    
    public int getPriority() { 
    	return priority; 
    }
    
    public void setPriority(int priority) { 
    	this.priority = priority; 
    }
    
    public int getStartTime() { 
    	return start_time; 
    }
    
    public void setStartTime(int start_time) { 
    	this.start_time = start_time; 
    }
    
    public String getName() { 
    	return this.name; 
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }    

    
    @Override
    public int compareTo(Object o) {
        Process p = (Process) o;
        return this.arrival_time < p.arrival_time ? -1 : 1;
    }
    
    @Override 
    public Object clone() throws CloneNotSupportedException {
        Process cloned = new Process();
        cloned.name = this.name;
        cloned.arrival_time = this.arrival_time;
        cloned.priority = this.priority;
        cloned.burst_time = this.burst_time;
        cloned.start_time = this.start_time;
        return cloned;
    }
    
    @Override
    public String toString() 
    {	
    	String processPrint = "    Process "+name+" [arrivalTime = "+arrival_time+" | burstTime = "+burst_time+" | priority = "+priority+"]"; 
        return processPrint;
    }	
}
