package com.c0d3m4513r.pluginapiimpl.spigot_v112;

import org.bukkit.permissions.Permissible;

public interface Permission extends Permissible, com.c0d3m4513r.pluginapi.Permission {
    @Override
    default boolean hasPerm(String perm) {
        return hasPermission(perm);
    }
}
