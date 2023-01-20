package com.westeros.tools.schedulers;

import com.westeros.tools.schedulers.abstractions.ICompleteTasks;
import com.westeros.tools.schedulers.abstractions.IHandleExceptions;
import com.westeros.tools.schedulers.abstractions.IProvideNextExecutionTime;
import com.westeros.tools.schedulers.abstractions.IRunNotSafeAction;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


public class Job {
    private IRunNotSafeAction action;
    private IProvideNextExecutionTime nextTimeProvider = ()-> null;
    private IHandleExceptions handleExceptions = ex->{};
    private ICompleteTasks singleActionCompleted = ()->{};
    private ICompleteTasks completed=()->{};
    private LocalDateTime executionTime = LocalDateTime.now();


    public Job(IRunNotSafeAction action) {
        this.action = action;
    }

    public Job useExecutionTimeProvider(IProvideNextExecutionTime nextTimeProvider) {
        this.nextTimeProvider = nextTimeProvider;
        this.executionTime = nextTimeProvider.provideTime();
        return this;
    }

    public Job onError(IHandleExceptions handleExceptions) {
        this.handleExceptions = handleExceptions;
        return this;
    }


    public Job onSingleActionCompleted(ICompleteTasks singleActionCompleted) {
        this.singleActionCompleted = singleActionCompleted;
        return this;
    }

    public Job onCompleted(ICompleteTasks completed) {
        this.completed = completed;
        return this;
    }

    public void schedule() {
        Scheduler.getInstance().addJob(this);
    }

    public void execute() {
        if(this.executionTime==null)return;
        try{
            if(this.executionTime.isBefore(LocalDateTime.now())) {
                System.out.println("EXECUTED");
                this.action.executeNotSafeAction();
                this.singleActionCompleted.complete();
            }
        }catch (Exception ex){
            this.handleExceptions.handle(ex);
        }finally {
            this.executionTime = nextTimeProvider.provideTime();
            if(this.executionTime==null)
                completed.complete();
        }
    }
}
