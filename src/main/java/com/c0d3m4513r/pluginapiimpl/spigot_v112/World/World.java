package com.c0d3m4513r.pluginapiimpl.spigot_v112.World;

import com.c0d3m4513r.pluginapi.entity.Player;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.BukkitViewer;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.Viewer;
import lombok.NonNull;
import lombok.Value;
import lombok.val;
import org.bukkit.SoundCategory;

import java.util.Collection;
import java.util.stream.Collectors;

@Value
public class World implements com.c0d3m4513r.pluginapi.World, BukkitViewer, Viewer {
    @NonNull
    org.bukkit.World world;

    @Override
    public Collection<Player> getPlayers() {
        return world.getPlayers()
                .parallelStream()
                .map(com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.Player::new)
                .collect(Collectors.toList());
    }

    @Override
    public BukkitViewer getViewer() {
        return this;
    }
    //--------------------------------------BUKKIT VIEWER IMPL----------------------------------------------------------
    @Override
    public void playSound(org.bukkit.Location location, org.bukkit.Sound sound, float volume, float pitch) {
        world.playSound(location,sound,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, String sound, float volume, float pitch) {
        world.playSound(location,sound,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, org.bukkit.Sound sound, SoundCategory category, float volume, float pitch) {
        world.playSound(location,sound,category,volume,pitch);
    }

    @Override
    public void playSound(org.bukkit.Location location, String sound, SoundCategory category, float volume, float pitch) {
        world.playSound(location,sound,category,volume,pitch);
    }

    @Override
    public void stopSound(org.bukkit.Sound sound) {
        for(val s:world.getPlayers()){
            s.stopSound(sound);
        }
    }

    @Override
    public void stopSound(String sound) {
        for(val s:world.getPlayers()){
            s.stopSound(sound);
        }
    }

    @Override
    public void stopSound(org.bukkit.Sound sound, SoundCategory category) {
        for(val s:world.getPlayers()){
            s.stopSound(sound,category);
        }
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        for(val s:world.getPlayers()){
            s.stopSound(sound,category);
        }
    }

    @Override
    @Deprecated
    public void sendTitle(String title, String subtitle) {
        for(val s:world.getPlayers()){
            s.sendTitle(title, subtitle);
        }
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        for(val s:world.getPlayers()){
            s.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    @Override
    public void resetTitle() {
        for(val s:world.getPlayers()){
            s.resetTitle();
        }
    }
    //--------------------------------------BUKKIT VIEWER IMPL----------------------------------------------------------
}
