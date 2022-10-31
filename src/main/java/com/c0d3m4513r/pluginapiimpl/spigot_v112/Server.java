package com.c0d3m4513r.pluginapiimpl.spigot_v112;

import com.c0d3m4513r.pluginapi.Task;
import com.c0d3m4513r.pluginapi.World;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.World.BukkitWorld;
import lombok.NonNull;
import lombok.val;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Server extends com.c0d3m4513r.pluginapi.Server {

    @Override
    public void execCommand(@NonNull String cmd) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
    }

    @Override
    protected boolean restart(Optional<String> reason) {
        reason.ifPresent(s->{
            for (val p:Bukkit.getServer().getOnlinePlayers()){
                p.kickPlayer(s);
            }
        });
        Bukkit.getServer().shutdown();
        return true;
    }

    @Override
    public Set<Task> getTasks() {
        return Bukkit.getScheduler()
                .getPendingTasks()
                .stream()
                .map(com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling.Task::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<World> getWorlds() {
        return Bukkit.getWorlds().parallelStream().map(BukkitWorld::new).map(com.c0d3m4513r.pluginapiimpl.spigot_v112.World.World::new).collect(Collectors.toList());
    }

    @Override
    public boolean isMainThread() {
        return Bukkit.isPrimaryThread();
    }

    @Override
    public void sendMessage(@NonNull String message) {
        Bukkit.broadcastMessage(message);
    }
}
