package com.c0d3m4513r.pluginapiimpl.spigot_v112.command;

import com.c0d3m4513r.pluginapi.command.Command;
import com.c0d3m4513r.pluginapi.command.CommandMapping;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class SpigotCommandMapping implements CommandMapping {
    @NonNull
    List<String> aliases;
    @NonNull
    Command command;
    @Override
    public String getPrimaryAlias() {
        return aliases.get(0);
    }

    @Override
    public Set<String> getAllAliases() {
        return new HashSet<>(aliases);
    }

    @Override
    public Command getCallable() {
        return command;
    }
}
