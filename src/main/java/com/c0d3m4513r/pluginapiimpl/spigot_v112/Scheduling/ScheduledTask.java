package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapi.config.TimeUnitValue;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Data
@Setter(AccessLevel.NONE)
@Deprecated
class ScheduledTask implements com.c0d3m4513r.pluginapi.Task,Comparable<ScheduledTask>{
    ScheduledTask(@NonNull TaskBuilder tb){
        builder=tb;
        nextExecution=Instant.now();

        if(tb.isDeferred()) setNextExecution(tb.getDeferredTimeAmount(), tb.getDeferredTimeValue());
        else if (tb.isTimer()) setNextExecution(tb.getTimerTimeAmount(), tb.getTimerTimeValue());
        //else just run as fast as possible
        //Due to needing to remove this task from the TaskScheduler we need to wait for the task scheduler to call the run method
    }

    /**
     * Runs the underlying Runnable, if enough time has been passed
     * @return if this task should be scheduled again
     */
    boolean run(){
        //it isn't time to execute yet
        if(Instant.now().isBefore(nextExecution)) return true;
        //the task has been cancelled.
        //remove it now, and don't run the task another time
        else if (cancelled) return true;
        //the task scheduler runs as an async thread itself.
        //it should be fine to just execute it here
        else if(builder.isAsync()) try{
            builder.getRun().run();
        }catch (Throwable e){
            API.getLogger().error(String.format("Task %s threw a error:", builder.getName()),e);
        }
        //the task is sync. We do need to schedule the task to be run
        else Bukkit.getScheduler().runTask(TaskBuilder.getPlugin(),builder.getRun());

        if (builder.isTimer()){
            setNextExecution(builder.getTimerTimeAmount(), builder.getTimerTimeValue());
            return true;
        }
        else return false;
    }
    private void setNextExecution(long amount,TimeUnit unit){
        switch (unit){
            case DAYS:
            case HOURS:
            case MINUTES:
            case SECONDS:
                nextExecution=nextExecution.plusSeconds(TimeUnit.SECONDS.convert(amount,unit));
                return;
            case MILLISECONDS:
                nextExecution=nextExecution.plusMillis(amount);
                return;
            case MICROSECONDS:
            case NANOSECONDS:
                nextExecution=nextExecution.plusNanos(TimeUnit.NANOSECONDS.convert(amount, unit));
                return;
            default:
                throw new RuntimeException("More TimeUnit variants than expected!");
        }
    }
    @NonNull
    TaskBuilder builder;
    @NonNull
    Instant nextExecution;
    boolean cancelled=false;

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
        cancelled=true;
        return true;
    }

    @Override
    public int compareTo(@NonNull ScheduledTask o) {
        if(o.equals(this)) return 0;
        else if(o.getBuilder().isTimer() && !builder.isTimer()) return 1;
        else if(!o.getBuilder().isTimer() && builder.isTimer()) return -1;
        else if(o.getBuilder().isTimer() && builder.isTimer()){
            TimeUnitValue otuv = new TimeUnitValue(o.getBuilder().getTimerTimeValue(),o.getBuilder().getTimerTimeAmount());
            TimeUnitValue tuv = new TimeUnitValue(builder.getTimerTimeValue(),builder.getTimerTimeAmount());
            return otuv.compareTo(tuv);
        }
        else if (o.getBuilder().isDeferred() && !builder.isDeferred() && !builder.isTimer()) return 1;
        else if (!o.getBuilder().isDeferred() && !o.builder.isTimer() && builder.isDeferred()) return -1;
        else if (o.getBuilder().isDeferred() && builder.isDeferred()) {
            TimeUnitValue otuv = new TimeUnitValue(o.getBuilder().getDeferredTimeValue(),o.getBuilder().getDeferredTimeAmount());
            TimeUnitValue tuv = new TimeUnitValue(builder.getDeferredTimeValue(),builder.getDeferredTimeAmount());
            return otuv.compareTo(tuv);
        } else if (o.builder.isAsync() && !builder.isAsync()) return 1;
        else if (!o.builder.isAsync() && builder.isAsync()) return -1;
        else return Integer.compare(hashCode(),o.hashCode());
    }
}
