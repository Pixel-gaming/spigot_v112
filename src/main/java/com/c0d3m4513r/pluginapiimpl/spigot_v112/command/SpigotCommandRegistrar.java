package com.c0d3m4513r.pluginapiimpl.spigot_v112.command;

import com.c0d3m4513r.pluginapi.command.Command;
import com.c0d3m4513r.pluginapi.command.CommandMapping;
import com.c0d3m4513r.pluginapi.command.CommandRegistrar;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Plugin;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.bukkit.Bukkit.getServer;

@RequiredArgsConstructor
public class SpigotCommandRegistrar implements CommandRegistrar {
    @NonNull
    Plugin plugin;
    @Override
    public Optional<CommandMapping> register(Command command, List<String> alias) {
        CommandMap commandMap = null;
        try{
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap)(field.get(getServer().getPluginManager()));
        }catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        com.c0d3m4513r.pluginapiimpl.spigot_v112.command.Command com =
                new com.c0d3m4513r.pluginapiimpl.spigot_v112.command.Command(
                        alias.get(0),
                        command
                );
        com.setAliases(alias);

        assert commandMap != null;
        commandMap.register(plugin.getName(), com);


        return Optional.of(new SpigotCommandMapping(com.getAliases(),command));
    }
}
