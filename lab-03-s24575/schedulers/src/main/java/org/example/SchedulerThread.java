package org.example;

import java.time.LocalDateTime;
import java.util.List;

import org.example.scheduler.abstractions.Scheduler;
import org.example.scheduler.abstractions.Task;

public class SchedulerThread implements Runnable {
    private Scheduler scheduler = null;
    public SchedulerThread(){
        this.scheduler = Scheduler.getInstance();
    }
    @Override
    public void run(){
        List<Task> tasks = scheduler.getNotExecuted();
        for(int i = 0; i < tasks.size(); ++i){
            Task task = tasks.get(i);
            if(!task.isRunning()){
                if(task.getiProvideNextExecutionTime() == null) continue;
                Chron chron = task.getiProvideNextExecutionTime().provide();
                if(chron.getStartTime().isBefore(LocalDateTime.now())){
                    new Thread(()->{
                        int x = chron.getMaxExecutionTimes();
                        boolean hasFinished = true;
                        while (0 < x-- && chron.getEndTime().isAfter(LocalDateTime.now())){
                            try {
                                if(task.getNotSafeAction() != null) task.getNotSafeAction().executeNotSafeAction();
                            }catch (Exception e){
                                try{
                                    if(task.getOnError() != null) task.getOnError().execute(e);
                                    hasFinished = false;
                                    break;
                                } catch(Exception ex){}
                            }
                            if(task.getExecuteOnActionFinished() != null) task.getExecuteOnActionFinished().execute();
                            try{
                                Thread.sleep(chron.getIntervalDuration().toMillis());
                            } catch(Exception e){}
                        }
                        if(task.getExecuteOnCompleted() != null && hasFinished)task.getExecuteOnCompleted().execute();
                    }).start();
                    task.setRunning(true);
                }
            }
        }
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            System.out.println("Error: couldn't sleep.");
        }
        run();
    }
}
