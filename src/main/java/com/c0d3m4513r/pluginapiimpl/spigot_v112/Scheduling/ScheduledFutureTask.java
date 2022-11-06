package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import com.c0d3m4513r.pluginapi.Task;
import lombok.Value;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Value
public class ScheduledFutureTask<T> implements Task {
    ScheduledFuture<T> future;
    TaskBuilder builder;

    @Override
    public String getName() {
        return builder.getName();
    }

    @Override
    public long getDelay() {
        return future.getDelay(TimeUnit.MILLISECONDS);
    }

    @Override
    public long getInterval() {
        return TimeUnit.MILLISECONDS.convert(builder.getTimerTimeAmount(),builder.getTimerTimeValue());
    }

    @Override
    public boolean isAsynchronous() {
        return builder.isAsync();
    }

    @Override
    public boolean cancel() {
        return future.cancel(false);
    }
}
