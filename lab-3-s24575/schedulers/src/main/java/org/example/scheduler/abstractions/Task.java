package org.example.scheduler.abstractions;

import org.example.Chron;

public class Task{
    private boolean isRunning = false;
    private IRunNotSafeAction notSafeAction = null;
    private IProvideNextExecutionTime<Chron> iProvideNextExecutionTime = null;
    private ExecuteOnError onError = null;
    private ExecuteOnActionFinished executeOnActionFinished = null;
    private ExecuteOnCompleted executeOnCompleted = null;

    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    public IRunNotSafeAction getNotSafeAction() {
        return notSafeAction;
    }
    public void setNotSafeAction(IRunNotSafeAction notSafeAction) {
        this.notSafeAction = notSafeAction;
    }
    public IProvideNextExecutionTime<Chron> getiProvideNextExecutionTime() {
        return iProvideNextExecutionTime;
    }
    public void setiProvideNextExecutionTime(IProvideNextExecutionTime<Chron> iProvideNextExecutionTime) {
        this.iProvideNextExecutionTime = iProvideNextExecutionTime;
    }
    public ExecuteOnError getOnError() {
        return onError;
    }
    public void setOnError(ExecuteOnError onError) {
        this.onError = onError;
    }
    public ExecuteOnActionFinished getExecuteOnActionFinished() {
        return executeOnActionFinished;
    }
    public void setExecuteOnActionFinished(ExecuteOnActionFinished executeOnActionFinished) {
        this.executeOnActionFinished = executeOnActionFinished;
    }
    public ExecuteOnCompleted getExecuteOnCompleted() {
        return executeOnCompleted;
    }
    public void setExecuteOnCompleted(ExecuteOnCompleted executeOnCompleted) {
        this.executeOnCompleted = executeOnCompleted;
    }
}