package com.c0d3m4513r.pluginapiimpl.spigot_v112.Registry;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Sound extends com.c0d3m4513r.pluginapi.registry.Sound {
    //This exists, so I can access something in this class, to run the static block
    public static final Object none = null;
    static {
        new Sound();
    }
    boolean bukkitSoundEnum;
    org.bukkit.Sound bukkitSound;
    String stringSound;

    public Sound(){
        this("block.note.pling");
    }
    public Sound(@NonNull org.bukkit.Sound soundType){
        if (sound==null) sound=this;
        bukkitSoundEnum=true;
        bukkitSound =soundType;
        stringSound=null;
    }
    public Sound(@NonNull String soundType){
        if(sound==null) sound=this;
        bukkitSoundEnum=false;
        bukkitSound=null;
        stringSound=soundType;
    }

    @Override
    protected Sound getSoundInt(String key) {
        try{
            return new Sound(org.bukkit.Sound.valueOf(key));
        }catch (IllegalArgumentException ignore){
            return new Sound(key);
        }
    }
}
