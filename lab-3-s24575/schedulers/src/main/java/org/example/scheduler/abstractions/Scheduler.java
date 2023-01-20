package org.example.scheduler.abstractions;

import org.example.Chron;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private static Scheduler INSTANCE;
    private Scheduler(){}
    public static Scheduler getInstance(){
        if(INSTANCE == null) INSTANCE = new Scheduler();
        return INSTANCE;
    }

    private List<Task> notExecuted = new ArrayList<>();
    private Task current = new Task();

    public List<Task> getNotExecuted(){
        return notExecuted;
    }

    // Builder
    public Scheduler forAction(IRunNotSafeAction notSafeAction){
        current.setNotSafeAction(notSafeAction);
        return INSTANCE;
    }

    public Scheduler useExecutionTimeProvider(IProvideNextExecutionTime<Chron> iProvideNextExecutionTime){
        current.setiProvideNextExecutionTime(iProvideNextExecutionTime);
        return INSTANCE;
    }

    public Scheduler onError(ExecuteOnError onError){
        current.setOnError(onError);
        return INSTANCE;
    }

    public Scheduler onSingleActionCompleted(ExecuteOnActionFinished executeOnActionFinished){
        current.setExecuteOnActionFinished(executeOnActionFinished);
        return INSTANCE;
    }

    public Scheduler onCompleted(ExecuteOnCompleted executeOnCompleted){
        current.setExecuteOnCompleted(executeOnCompleted);
        return INSTANCE;
    }

    public void Schedule(){
        notExecuted.add(current);
        current = new Task();
    }
}