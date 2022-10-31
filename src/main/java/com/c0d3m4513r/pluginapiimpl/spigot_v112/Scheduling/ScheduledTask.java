package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Data
@Setter(AccessLevel.NONE)
public class ScheduledTask implements com.c0d3m4513r.pluginapi.Task{
    ScheduledTask(@NonNull TaskBuilder tb){
        builder=tb;

        if(tb.isDeferred()) setNextExecution(tb.getDeferredTimeAmount(), tb.getDeferredTimeValue());
        else if (tb.isTimer()) setNextExecution(tb.getTimerTimeAmount(), tb.getTimerTimeValue());
        else {
            nextExecution=Instant.now();
            run();
        }
    }
    void run(){
        if(Instant.now().isBefore(nextExecution)) return;
        if(builder.isAsync()) builder.getRun().run();
        else Bukkit.getScheduler().runTask(TaskBuilder.getPlugin(),builder.getRun());

        if (builder.isTimer()) setNextExecution(builder.getTimerTimeAmount(), builder.getTimerTimeValue());
        else cancel();
    }
    private void setNextExecution(long amount,TimeUnit unit){
        switch (unit){
            case DAYS:
            case HOURS:
            case MINUTES:
            case SECONDS:
                nextExecution=Instant.now().plusSeconds(TimeUnit.SECONDS.convert(amount,unit));
                return;
            case MILLISECONDS:
                nextExecution=Instant.now().plusMillis(amount);
                return;
            case MICROSECONDS:
            case NANOSECONDS:
                nextExecution=Instant.now().plusNanos(TimeUnit.NANOSECONDS.convert(amount, unit));
                return;
            default:
                throw new RuntimeException("More TimeUnit variants than expected!");
        }
    }
    @NonNull
    TaskBuilder builder;
    @NonNull
    Instant nextExecution;

    @Override
    public String getName() {
        return builder.getName();
    }

    @Override
    public long getDelay() {
        return TimeUnit.MILLISECONDS.convert(builder.getDeferredTimeAmount(), builder.getDeferredTimeValue());
    }

    @Override
    public long getInterval() {
        return TimeUnit.MILLISECONDS.convert(builder.getTimerTimeAmount(), builder.getTimerTimeValue());
    }

    @Override
    public boolean isAsynchronous() {
        return builder.isAsync();
    }

    @Override
    public boolean cancel() {
        return TaskScheduler.cancel(this);
    }
}
