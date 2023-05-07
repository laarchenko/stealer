package com.example.stealer.parsing;

import com.example.stealer.entity.Site;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.*;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Parser implements RunnableScheduledFuture {

    Integer timeout;

    ParsingResult result;

    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public long getDelay(@org.jetbrains.annotations.NotNull TimeUnit timeUnit) {
        return 0;
    }

    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull Delayed delayed) {
        return 0;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    Site site;

    @Override
    public void run() {
        result = execute(site);
    }

    abstract ParsingResult execute(Site site);//TODO add items to site with item validation
}
