package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling;

import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

@RequiredArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class Task implements com.c0d3m4513r.pluginapi.Task {
    @NonNull
    BukkitTask task;
    @Override
    public String getName() {
        return Integer.toString(task.getTaskId());
    }

    @Override
    public long getDelay() {
        return Long.MIN_VALUE;
    }

    @Override
    public long getInterval() {
        return Long.MIN_VALUE;
    }

    @Override
    public boolean isAsynchronous() {
        return !task.isSync();
    }

    @Override
    //Cannot make sure, that the task will not repeat. Hope for the best!
    public boolean cancel() {
        Bukkit.getScheduler();
        task.cancel();
        return true;
    }
}
