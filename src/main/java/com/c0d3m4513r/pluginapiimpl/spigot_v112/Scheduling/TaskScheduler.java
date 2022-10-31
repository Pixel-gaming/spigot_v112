package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;


import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapi.Task;
import com.c0d3m4513r.pluginapi.events.EventRegistrar;
import com.c0d3m4513r.pluginapi.events.EventType;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Plugin;
import lombok.NonNull;
import lombok.val;
import org.bukkit.scheduler.BukkitTask;

import java.util.LinkedList;
import java.util.List;

public class TaskScheduler extends TaskBuilder{

    public static TaskScheduler scheduler;
    @NonNull
    private static List<ScheduledTask> tasks = new LinkedList<>();

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
        run=this::run;
        task=buildInt();
        //register everything to be destroyed
        new EventRegistrar(this::shutdown, EventType.onReboot,1);
    }

    private void run(){
        for(val t:tasks){
            t.run();
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

    static boolean cancel(ScheduledTask task){
        return tasks.remove(task);
    }
    private void shutdown(){
        API.getLogger().info("Shutting down TaskScheduler! Server is Restarting.");
        task.cancel();
        tasks = new LinkedList<>();
    }

}
