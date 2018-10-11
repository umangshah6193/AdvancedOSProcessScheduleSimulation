package com.aos.utils;

public class Parameters {

    private int turn_around_time;
    private int waiting_time;
    private int response_time;
    private int process_count;
    private int quanta;
    
    private int turn_around_time_per_run;
    private int waiting_time_per_run;
    private int response_time_per_run;
    private int process_count_per_run;
    private int quanta_per_run;
    
    public double getAvgTurnaroundTime()
    {
        return turn_around_time / (double) process_count;
    }
    
    public double getAvgTurnaroundTimeForRun()
    {
        return turn_around_time_per_run / (double) process_count_per_run;
    }
    
    public double getAvgWaitingTime()
    {
        return waiting_time / (double) process_count;
    }
    
    public double getAvgWaitingTimeForRun()
    {
        return waiting_time_per_run / (double) process_count_per_run;
    }
    
    public double getAvgResponseTime()
    {
        return response_time / (double) process_count;
    }
    
    public double getAvgResponseTimeForRun()
    {
        return response_time_per_run / (double) process_count_per_run;
    }
    
    public double getAvgThroughput()
    {
        return 100 * process_count / (double) quanta;
    }
    
    public double getAvgThroughputForRun()
    {
        return 100 * process_count_per_run / (double) quanta_per_run;
    }
    
    public void incrementWaitTime(double time)
    {
        waiting_time += time;
        waiting_time_per_run += time;
    }
    
    public void incrementResponseTime(double time)
    {
        response_time += time;
        response_time_per_run += time;
    }
    
    public void incrementTurnAroundTime(double time)
    {
        turn_around_time += time;
        turn_around_time_per_run += time;
    }
    
    public void incrementProcess()
    {
        ++process_count;
        ++process_count_per_run;
    }
    
    public void incrementQuanta(int quantaCount)
    {
        quanta += quantaCount;
        quanta_per_run += quantaCount;
    }
    
    public void reset()
    {
        turn_around_time_per_run = 0;
        waiting_time_per_run = 0;
        response_time_per_run = 0;
        process_count_per_run = 0;
        quanta_per_run = 0;
    }
}
