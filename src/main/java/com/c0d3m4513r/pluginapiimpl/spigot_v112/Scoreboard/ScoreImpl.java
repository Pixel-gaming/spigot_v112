package com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard;

import com.c0d3m4513r.pluginapi.Scoreboard.Score;
import lombok.Data;
import lombok.NonNull;

@Data
public class ScoreImpl implements Score {
    @NonNull
    org.bukkit.scoreboard.Score bukkitScore;

    @Override
    public int getScore() {
        return bukkitScore.getScore();
    }

    @Override
    public String getName() {
        return bukkitScore.getObjective().getName();
    }

    @Override
    public void setScore(int score) {
        bukkitScore.setScore(score);
    }
}
