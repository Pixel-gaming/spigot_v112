package com.c0d3m4513r.pluginapiimpl.spigot_v112.World;

import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.BukkitViewer;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.entity.Player;
import lombok.Value;
import lombok.val;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;

@Value
public class BukkitWorld implements BukkitViewer {
    World w;

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        w.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        w.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
        w.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {
        w.playSound(location, sound, category, volume, pitch);
    }

    public void stopSound(org.bukkit.Sound sound) {
        for(val s:w.getPlayers()){
            s.stopSound(sound);
        }
    }

    public void stopSound(String sound) {
        for(val s:w.getPlayers()){
            s.stopSound(sound);
        }
    }

    public void stopSound(org.bukkit.Sound sound, SoundCategory category) {
        for(val s:w.getPlayers()){
            s.stopSound(sound,category);
        }
    }

    public void stopSound(String sound, SoundCategory category) {
        for(val s:w.getPlayers()){
            s.stopSound(sound,category);
        }
    }
    @Deprecated
    public void sendTitle(String title, String subtitle){
        for(val p:w.getPlayers()){
            new Player(p).sendTitle(title,subtitle);
        }
    }

    /**
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. All timings values may take a value of -1 to indicate that they
     * will use the last value sent (or the defaults if no title has been
     * displayed).
     *
     * @param title Title text
     * @param subtitle Subtitle text
     * @param fadeIn time in ticks for titles to fade in. Defaults to 10.
     * @param stay time in ticks for titles to stay. Defaults to 70.
     * @param fadeOut time in ticks for titles to fade out. Defaults to 20.
     */
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut){
        for(val p:w.getPlayers()){
            new Player(p).sendTitle(title,subtitle,fadeIn,stay,fadeOut);
        }
    }

    /**
     * Resets the title displayed to the player. This will clear the displayed
     * title / subtitle and reset timings to their default values.
     */
    public void resetTitle(){
        for(val p:w.getPlayers()){
            new Player(p).resetTitle();
        }
    }
}
