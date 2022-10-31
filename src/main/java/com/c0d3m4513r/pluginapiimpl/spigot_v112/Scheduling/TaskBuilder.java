package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import com.c0d3m4513r.pluginapi.Nullable;
import com.c0d3m4513r.pluginapi.Task;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Plugin;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;
@Getter
public abstract class TaskBuilder extends com.c0d3m4513r.pluginapi.TaskBuilder {
    protected boolean async = false;
    protected boolean timer = false;
    protected long timerTimeAmount = 0;
    protected TimeUnit timerTimeValue = null;
    protected boolean deferred = false;
    protected long deferredTimeAmount = 0;
    protected TimeUnit deferredTimeValue = null;
    protected Runnable run = null;

    protected String name = null;
    protected static Plugin plugin;

    public static Plugin getPlugin(){
        return plugin;
    }

    protected TaskBuilder(){}
    public TaskBuilder(TaskBuilder tb){
        async=tb.async;
        timer=tb.timer;
        timerTimeAmount=tb.timerTimeAmount;
        timerTimeValue=tb.timerTimeValue;
        deferred=tb.deferred;
        deferredTimeAmount=tb.deferredTimeAmount;
        deferredTimeValue=tb.deferredTimeValue;
        run=tb.run;
        name=tb.name;
    }

    @Override
    public @NonNull TaskBuilder async(boolean async) {
        this.async=async;
        return this;
    }

    @Override
    public @NonNull TaskBuilder deferred(long timeAmount, @NonNull TimeUnit timeValue) {
        if (timeAmount!=0){
            deferred=true;
            deferredTimeAmount=timeAmount;
            deferredTimeValue=timeValue;
        }else{
            deferred=false;
            deferredTimeAmount=timeAmount;
            deferredTimeValue=null;
        }
        return this;
    }

    @Override
    public @NonNull TaskBuilder timer(long timeAmount, @NonNull TimeUnit timeValue) {
        if (timeAmount!=0){
            timer=true;
            timerTimeAmount=timeAmount;
            timerTimeValue=timeValue;
        }else{
            timer=false;
            timerTimeAmount=timeAmount;
            timerTimeValue=null;
        }
        return this;
    }


    @Override
    public @NonNull TaskBuilder executer(@NonNull Runnable run) {
        this.run=run;
        return this;
    }

    @Override
    public @NonNull TaskBuilder name(@Nullable String name) {
        this.name=name;
        return this;
    }

    @Override
    public @NonNull TaskBuilder reset() {
        async = false;
        timer = false;
        timerTimeAmount = 0;
        timerTimeValue = null;
        deferred = false;
        deferredTimeAmount = 0;
        deferredTimeValue = null;
        run = null;
        return this;
    }

    @NonNull
    protected BukkitTask buildInt() throws IllegalArgumentException{
        BukkitTask task;
        if (async){
            if (timer){
                task=Bukkit.getScheduler().runTaskTimerAsynchronously(plugin,run,deferredTimeAmount,timerTimeAmount);
            }else if (deferred){
                task=Bukkit.getScheduler().runTaskLaterAsynchronously(plugin,run,deferredTimeAmount);
            }else{
                task=Bukkit.getScheduler().runTaskAsynchronously(plugin,run);
            }
        }else{
            if (timer){
                task=Bukkit.getScheduler().runTaskTimer(plugin,run,deferredTimeAmount,timerTimeAmount);
            }else if (deferred){
                task=Bukkit.getScheduler().runTaskLater(plugin,run,deferredTimeAmount);
            }else {
                task=Bukkit.getScheduler().runTask(plugin,run);
            }
        }
        reset();
        return task;
    }
}
