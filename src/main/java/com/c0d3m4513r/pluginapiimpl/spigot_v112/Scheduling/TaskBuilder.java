package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import com.c0d3m4513r.pluginapi.Nullable;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Plugin;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class TaskBuilder extends com.c0d3m4513r.pluginapi.TaskBuilder {
    private boolean async = false;
    private boolean timer = false;
    private long timerTimeAmount = 0;
    private TimeUnit timerTimeValue = null;
    private boolean deferred = false;
    private long deferredTimeAmount = 0;
    private TimeUnit deferredTimeValue = null;
    private Runnable run = null;

    private String name = null;
    private static Plugin plugin;


    public TaskBuilder(Plugin plugint){
        TaskBuilder.plugin=plugint;
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
    public com.c0d3m4513r.pluginapi.@NonNull TaskBuilder name(@Nullable String name) {
        this.name=name;
        return this;
    }

    @Override
    public com.c0d3m4513r.pluginapi.@NonNull TaskBuilder reset() {
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

    @Override
    @NonNull
    public com.c0d3m4513r.pluginapi.Task build() throws IllegalArgumentException{
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
        return new com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling.Task(task);
    }
}
