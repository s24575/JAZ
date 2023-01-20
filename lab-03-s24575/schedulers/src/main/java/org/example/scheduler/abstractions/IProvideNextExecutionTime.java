package org.example.scheduler.abstractions;

@FunctionalInterface
public interface IProvideNextExecutionTime<T> {
    T provide();
}