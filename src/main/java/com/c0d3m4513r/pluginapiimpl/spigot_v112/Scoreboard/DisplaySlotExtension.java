package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard;

import com.c0d3m4513r.pluginapi.Scoreboard.DisplaySlot;

public class DisplaySlotExtension {

    public static org.bukkit.scoreboard.DisplaySlot ConvertDisplaySlot(DisplaySlot displaySlot){
        switch (displaySlot){
            case List:
                return org.bukkit.scoreboard.DisplaySlot.PLAYER_LIST;
            case Below_Name:
                return org.bukkit.scoreboard.DisplaySlot.BELOW_NAME;
            case Sidebar:
                return org.bukkit.scoreboard.DisplaySlot.SIDEBAR;
            default:
                throw new RuntimeException("Unknown DisplaySlot value");
        }
    }
}
