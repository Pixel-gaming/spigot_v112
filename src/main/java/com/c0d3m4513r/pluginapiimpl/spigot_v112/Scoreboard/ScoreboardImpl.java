package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard;

import com.c0d3m4513r.pluginapi.Nullable;
import com.c0d3m4513r.pluginapi.Scoreboard.DisplaySlot;
import com.c0d3m4513r.pluginapi.Scoreboard.Objective;
import com.c0d3m4513r.pluginapi.Scoreboard.Scoreboard;
import lombok.NonNull;
import lombok.Value;
import org.bukkit.Bukkit;
@Value
public class ScoreboardImpl extends Scoreboard {
    //This exists, so I can access something in this class, to run the static block
    public static final Object none = null;
    static {
        constructor = ScoreboardImpl::new;
    }
    org.bukkit.scoreboard.Scoreboard bukkitScoreboard;
    public ScoreboardImpl(org.bukkit.scoreboard.Scoreboard bukkitScoreboard){
        this.bukkitScoreboard = bukkitScoreboard;
    }
    private ScoreboardImpl(){
        this(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
    }

    @Override
    public void addObjective(Objective objective) throws IllegalArgumentException {
        bukkitScoreboard.registerNewObjective(objective.getName(),"Dummy");
    }

    @Override
    public void updateDisplaySlot(@Nullable Objective objective, @NonNull DisplaySlot displaySlot) throws IllegalStateException {
        final org.bukkit.scoreboard.DisplaySlot bukkitDisplaySlot;
        switch (displaySlot){
            case List:
                bukkitDisplaySlot = org.bukkit.scoreboard.DisplaySlot.PLAYER_LIST;
                break;
            case Below_Name:
                bukkitDisplaySlot = org.bukkit.scoreboard.DisplaySlot.BELOW_NAME;
                break;
            case Sidebar:
                bukkitDisplaySlot = org.bukkit.scoreboard.DisplaySlot.SIDEBAR;
                break;
            default:
                throw new RuntimeException("Unknown DisplaySlot value");
        }
        org.bukkit.scoreboard.Objective bukkitObjective;
        if (objective!=null){
            bukkitObjective=((ObjectiveImpl)objective).getObjective();
        }else{
            bukkitObjective = bukkitScoreboard.getObjective(bukkitDisplaySlot);
            if (bukkitObjective==null) return;
        }
        bukkitObjective.setDisplaySlot(bukkitDisplaySlot);
    }

    @Override
    public void removeObjective(Objective objective) {
        ((ObjectiveImpl)objective).getObjective().unregister();
    }
}
