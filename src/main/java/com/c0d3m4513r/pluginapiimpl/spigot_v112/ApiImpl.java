package com.c0d3m4513r.pluginapiimpl.spigot_v112;

import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Registry.Sound;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard.ObjectiveImpl;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard.ScoreboardImpl;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.command.CommandResult;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling.TaskBuilder;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.command.SpigotCommandRegistrar;
import lombok.val;

import java.util.logging.Logger;

public class ApiImpl extends API {
    ApiImpl(Logger logger,Plugin plugin){
        super();
        API.logger = new com.c0d3m4513r.pluginapiimpl.spigot_v112.Logger(logger);
        API.server = new Server();
        API.commandRegistrar=new SpigotCommandRegistrar(plugin);

        configLoader = plugin;

        commandRegistrar = new SpigotCommandRegistrar(plugin);
        commandResult = new CommandResult();
        config.main();

        new TaskBuilder(plugin);
        val none = ScoreboardImpl.none;
        val none1 = ObjectiveImpl.none;
        val none2 = Sound.none;

    }
}
