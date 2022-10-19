package com.c0d3m4513r.pluginapiimpl.spigot_v112.entity;

import com.c0d3m4513r.pluginapi.Scoreboard.Scoreboard;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Scoreboard.ScoreboardImpl;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.World.Location;
import lombok.NonNull;
import lombok.Value;
import org.bukkit.SoundCategory;


@Value
public class Player implements com.c0d3m4513r.pluginapi.entity.Player, BukkitViewer,Viewer {
    @NonNull
    org.bukkit.entity.Player player;

    @Override
    public boolean hasPerm(String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public String getIdentifier() {
        return player.getUniqueId().toString();
    }

    @Override
    public void sendMessage(@NonNull String message) {
        player.sendMessage(message);
    }

    @Override
    public BukkitViewer getViewer() {
        return this;
    }

    @Override
    public Scoreboard getScoreboard() {
        return new ScoreboardImpl(player.getScoreboard());
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        player.setScoreboard(((ScoreboardImpl)scoreboard).getBukkitScoreboard());
    }

    @Override
    public com.c0d3m4513r.pluginapi.world.Location getLocation() {
        return new Location(player.getLocation());
    }
//--------------------------------------BUKKIT VIEWER IMPL----------------------------------------------------------
    @Override
    public void playSound(org.bukkit.Location location, org.bukkit.Sound sound, float volume, float pitch) {
        player.playSound(location,sound,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, String sound, float volume, float pitch) {
        player.playSound(location,sound,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, org.bukkit.Sound sound, SoundCategory category, float volume, float pitch) {
        player.playSound(location,sound,category,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, String sound, SoundCategory category, float volume, float pitch) {
        player.playSound(location,sound,category,volume,pitch);
    }

    @Override
    public void stopSound(org.bukkit.Sound sound) {
        player.stopSound(sound);
    }

    @Override
    public void stopSound(String sound) {
        player.stopSound(sound);
    }

    @Override
    public void stopSound(org.bukkit.Sound sound, SoundCategory category) {
        player.stopSound(sound,category);
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        player.stopSound(sound, category);
    }

    @Override
    @Deprecated
    public void sendTitle(String title, String subtitle) {
        player.sendTitle(title, subtitle);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void resetTitle() {
        player.resetTitle();
    }
    //--------------------------------------BUKKIT VIEWER IMPL----------------------------------------------------------
}
