package com.c0d3m4513r.pluginapiimpl.spigot_v112.World;

import com.c0d3m4513r.pluginapi.Data.Point3D;
import lombok.Value;

@Value
public class Location implements com.c0d3m4513r.pluginapi.world.Location {
    org.bukkit.Location location;
    @Override
    public Point3D getPosition() {
        return new Point3D(location.getX(),location.getY(),location.getZ());
    }
}
