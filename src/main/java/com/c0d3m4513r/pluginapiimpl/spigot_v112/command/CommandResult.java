package com.c0d3m4513r.pluginapiimpl.spigot_v112.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CommandResult implements com.c0d3m4513r.pluginapi.command.CommandResult {
    @Getter
    Boolean result=null;
    @Override
    public com.c0d3m4513r.pluginapi.command.CommandResult success() {
        return new CommandResult(true);
    }

    @Override
    public com.c0d3m4513r.pluginapi.command.CommandResult error() {
        return new CommandResult(false);
    }
}
