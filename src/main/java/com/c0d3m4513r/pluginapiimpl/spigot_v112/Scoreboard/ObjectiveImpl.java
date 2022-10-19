package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard;

import com.c0d3m4513r.pluginapi.Scoreboard.Score;
import com.c0d3m4513r.pluginapi.Scoreboard.Scoreboard;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.scoreboard.Criterias;
import org.bukkit.scoreboard.Objective;

@Data
public class ObjectiveImpl extends com.c0d3m4513r.pluginapi.Scoreboard.Objective {
    @NonNull
    public Objective objective;
    //This exists, so I can access something in this class, to run the static block
    public static final Object none = null;

    static {
        com.c0d3m4513r.pluginapi.Scoreboard.Objective.construct = ObjectiveImpl::new;
    }

    public ObjectiveImpl(@NonNull Objective build) {
        super();
        objective = build;
    }

    @Override
    public void setDisplayName(@NonNull String name) {
        objective.setDisplayName(name);
    }

    @Override
    public String getName() {
        return objective.getName();
    }

    @Override
    public Score getOrCreateScore(String name) {
        return new ScoreImpl(objective.getScore(name));
    }

    @Override
    public com.c0d3m4513r.pluginapi.Scoreboard.Objective createNewInstance(Scoreboard scoreboard, String name, String displayName, com.c0d3m4513r.pluginapi.Scoreboard.Criteria criteria) {
        return new ObjectiveImpl(scoreboard,name, displayName, criteria);
    }

    private static String getCriterion(com.c0d3m4513r.pluginapi.Scoreboard.Criteria criteria) {
        switch (criteria) {
            case Dummy:
                return "Dummy";
            case Trigger:
                return "Trigger";
            case Deaths:
                return Criterias.DEATHS;
            case Health:
                return Criterias.HEALTH;
            case PlayerKills:
                return Criterias.PLAYER_KILLS;
            case TotalKills:
                return Criterias.TOTAL_KILLS;
            default:
                throw new RuntimeException("Criteria had more variants, than expected");
        }
    }

    ObjectiveImpl(Scoreboard scoreboard,String name, String displayName, com.c0d3m4513r.pluginapi.Scoreboard.Criteria criteria) {
        org.bukkit.scoreboard.Scoreboard sb =((ScoreboardImpl)scoreboard).getBukkitScoreboard();
        Objective ob = sb.getObjective(name);
        if (ob!=null) ob.unregister();
        this.objective=sb.registerNewObjective(name,getCriterion(criteria));
        objective.setDisplayName(displayName);
    }
}
