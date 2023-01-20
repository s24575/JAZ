package org.example;

import org.example.scheduler.abstractions.IProvideNextExecutionTime;

import java.time.LocalDateTime;
import java.time.Duration;

public class Chron {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxExecutionTimes;
    private Duration intervalDuration;

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getMaxExecutionTimes() {
        return maxExecutionTimes;
    }

    public void setMaxExecutionTimes(int maxExecutionTime) {
        this.maxExecutionTimes = maxExecutionTime;
    }

    public Duration getIntervalDuration() {
        return intervalDuration;
    }

    public void setIntervalDuration(Duration intervalDuration) {
        this.intervalDuration = intervalDuration;
    }
    public static ChronBuilder builder() { return new ChronBuilder(); }
}

class ChronBuilder{
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime = LocalDateTime.MAX;
    private int maxExecutionTimes = Integer.MAX_VALUE;
    private Duration intervalDuration = Duration.ofSeconds(1);

    public ChronBuilder setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
        return this;
    }

    public ChronBuilder setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
        return this;
    }

    public ChronBuilder setMaxExecutionTimes(int maxExecutionTime){
        this.maxExecutionTimes = maxExecutionTime;
        return this;
    }

    public ChronBuilder setIntervalDuration(Duration intervalDuration){
        this.intervalDuration = intervalDuration;
        return this;
    }

    public IProvideNextExecutionTime<Chron> buildNextTimeExecutionProvider()
    {
        return ()->{
            Chron ch = new Chron();
            ch.setStartTime(startTime);
            ch.setEndTime(endTime);
            ch.setMaxExecutionTimes(maxExecutionTimes);
            ch.setIntervalDuration(intervalDuration);
            return ch;
        };
    }
}