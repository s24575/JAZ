package com.example.safe.repeaters;


public class Repeater implements IRepeater{
    String exceptionName;
    int counter;
    int retryCount;
    long delayTime;
    private final IRepeaterExceptionRegistry exceptionRegistry;

    public Repeater(IRepeaterExceptionRegistry exceptionRegistry) {
        this.exceptionRegistry = exceptionRegistry;
    }

    @Override
    public IRepeater For(Throwable exception) {
        var registryEntry = exceptionRegistry.EntryFor(exception);
        if(exceptionName == null || exceptionName.equals(registryEntry.exceptionName())){
            counter = 1;
            exceptionName = registryEntry.exceptionName();
            retryCount = registryEntry.retriesCount();
            delayTime = registryEntry.delay();
        }
        return this;
    }

    @Override
    public void retry() { counter++; }

    @Override
    public boolean shouldRetry() {
        return counter<=retryCount;
    }

    @Override
    public IRepeater waiting() {
        synchronized (this) {
            try {
                wait(delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
