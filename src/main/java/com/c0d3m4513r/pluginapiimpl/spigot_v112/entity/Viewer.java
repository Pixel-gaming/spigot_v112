package com.c0d3m4513r.pluginapiimpl.spigot_v112.entity;

import com.c0d3m4513r.pluginapi.messages.Title;
import com.c0d3m4513r.pluginapi.registry.Sound;
import com.c0d3m4513r.pluginapi.world.Location;

public interface Viewer extends com.c0d3m4513r.pluginapi.entity.Viewer {
    BukkitViewer getViewer();
    default void playSound(Sound sound, Location location, int volume){
        com.c0d3m4513r.pluginapiimpl.spigot_v112.Registry.Sound sound1 = ((com.c0d3m4513r.pluginapiimpl.spigot_v112.Registry.Sound)sound);
        if(sound1.isBukkitSoundEnum()){
            getViewer()
                    .playSound(((com.c0d3m4513r.pluginapiimpl.spigot_v112.World.Location)location).getLocation(),
                            sound1.getBukkitSound(),
                            (float) volume,
                            1.0f);
        }else{
            getViewer()
                    .playSound(((com.c0d3m4513r.pluginapiimpl.spigot_v112.World.Location)location).getLocation(),
                            sound1.getStringSound(),
                            (float) volume,
                            1.0f);
        }
    }
    default void sendTitle(Title title){
        getViewer().sendTitle(title.getTitle().orElse(null), title.getSubtitle().orElse(null),10,70,20);
    }
}
