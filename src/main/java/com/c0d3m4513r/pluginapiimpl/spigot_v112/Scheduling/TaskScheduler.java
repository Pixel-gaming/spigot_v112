package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;


import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapi.Task;
import com.c0d3m4513r.pluginapi.events.EventRegistrar;
import com.c0d3m4513r.pluginapi.events.EventType;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Plugin;
import lombok.NonNull;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

@Deprecated
class TaskScheduler extends TaskBuilder{

    public static TaskScheduler scheduler;
    @NonNull
    private static final Set<ScheduledTask> tasks = new ConcurrentSkipListSet<>();
    private static final Lock lock= new ReentrantLock();

    @NonNull
    private BukkitTask task;

    public TaskScheduler(){
        super();
        init();
        scheduler=this;
    }
    public TaskScheduler(@NonNull Plugin plugin){
        super();
        TaskBuilder.plugin = plugin;
        construct=TaskScheduler::new;
    }
    TaskScheduler(TaskScheduler s){
        super(s);
    }

    private void init(){
        async(true);
        timer=true;
        timerTimeValue=null;
        timerTimeAmount=1;
        deferred=false;
        deferredTimeAmount=0;
        run=this::run;
        task=buildInt();
        //register everything to be destroyed
        new EventRegistrar(this::shutdown, EventType.onReboot,1);
    }

    private void run(){
        if(lock.tryLock())
            try {
                tasks.removeIf(task->!task.run());
            } finally {
                lock.unlock();
            }
    }

    @Override
    public @NonNull Task build() throws IllegalArgumentException {
        return this.scheduleTask();
    }

    @NonNull ScheduledTask scheduleTask(){
        final ScheduledTask t = new ScheduledTask(new TaskScheduler(this));
        tasks.add(t);
        return t;
    }

    boolean cancel(ScheduledTask task){
        return tasks.remove(task);
    }
    private void shutdown(){
        API.getLogger().info("Shutting down TaskScheduler! Server is Restarting.");
        task.cancel();
        //this variable is necessary for types to work correctly
        List<ScheduledTask> l = Collections.emptyList();
        tasks.retainAll(l);
    }

}
