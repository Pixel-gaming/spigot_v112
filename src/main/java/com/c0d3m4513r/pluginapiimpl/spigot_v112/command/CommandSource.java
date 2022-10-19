package com.c0d3m4513r.pluginapiimpl.spigot_v112.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

@RequiredArgsConstructor
public class CommandSource implements com.c0d3m4513r.pluginapi.command.CommandSource {
    @NonNull
    @Getter(AccessLevel.PACKAGE)
    CommandSender commandSource;

    @Override
    public boolean hasPerm(String perm) {
        return commandSource.hasPermission(perm);
    }

    @Override
    public void sendMessage(@NonNull String message) {
        commandSource.sendMessage(message);
    }

    @Override
    public String getIdentifier() {
        if (commandSource instanceof Entity){
            return ((Entity) commandSource).getUniqueId().toString();
        }else{
            return commandSource.getName();
        }
    }
}
