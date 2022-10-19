package com.c0d3m4513r.pluginapiimpl.spigot_v112.command;

import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapi.command.CommandException;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Command extends org.bukkit.command.Command {
    @NonNull
    com.c0d3m4513r.pluginapi.command.Command command;

    public Command(String name, @NonNull com.c0d3m4513r.pluginapi.command.Command command) {
        super(name);
        this.command=command;
        CommandSource consoleSource = new CommandSource(Bukkit.getConsoleSender());
        command.getShortDescription(consoleSource).ifPresent(this::setDescription);
        this.setUsage(command.getUsage(consoleSource));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try{
            CommandResult result = (CommandResult) command.process(new CommandSource(sender),String.join(" ",args));
            return result.getResult();
        } catch (CommandException e) {
            API.getLogger().error("Error whilst executing command '/"+commandLabel+" "+String.join(" ",args)+"'. Message is '"+e.getMessage()+"'. Error is:",e.getE());
            return false;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        try {
            return command.getSuggestions(new CommandSource(sender),String.join(" ",args));
        } catch (CommandException e) {
            API.getLogger().warn("Error whilst executing command '"+alias+"' : ",e);
            API.getLogger().info("Falling back to default completer.");
            return super.tabComplete(sender,alias,args);
        }
    }


}
