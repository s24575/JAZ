package org.example.scheduler.abstractions;

@FunctionalInterface
public interface ExecuteOnError{
    void execute(Exception ex) throws Exception;
}