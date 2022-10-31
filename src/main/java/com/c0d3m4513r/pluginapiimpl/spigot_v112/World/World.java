package com.c0d3m4513r.pluginapiimpl.spigot_v112.World;

import com.c0d3m4513r.pluginapi.entity.Player;
import com.c0d3m4513r.pluginapi.world.Location;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.BukkitViewer;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.Viewer;
import lombok.NonNull;
import lombok.Value;
import lombok.val;
import org.bukkit.SoundCategory;

import java.util.Collection;
import java.util.stream.Collectors;

@Value
public class World implements com.c0d3m4513r.pluginapi.World, Viewer {
    @NonNull
    BukkitWorld world;

    @Override
    public Collection<Player> getPlayers() {
        return world.getW().getPlayers()
                .parallelStream()
                .map(com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.Player::new)
                .collect(Collectors.toList());
    }

    @Override
    public Location getSpawnLocation() {
        return new com.c0d3m4513r.pluginapiimpl.spigot_v112.World.Location(world.getW().getSpawnLocation());
    }

    @Override
    public BukkitViewer getViewer() {
        return world;
    }
}
