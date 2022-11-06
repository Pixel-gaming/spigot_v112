package com.c0d3m4513r.pluginapiimpl.spigot_v112;

import com.c0d3m4513r.pluginapi.API;
import com.c0d3m4513r.pluginapi.Nullable;
import com.c0d3m4513r.pluginapi.config.iface.IConfigLoaderSaver;
import com.c0d3m4513r.pluginapi.events.EventRegistrar;
import com.c0d3m4513r.pluginapi.events.EventType;
import com.c0d3m4513r.pluginapiimpl.spigot_v112.Scheduling.TaskBuilder;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class Plugin extends JavaPlugin implements IConfigLoaderSaver {
    API api;

    @Override
    public void onLoad() {
        try {
            saveDefaultConfig();//This only triggers, if the config file doesn't exist
            api=new ApiImpl(Bukkit.getLogger(),this);
            API.getConfig().loadValue();
            EventRegistrar.submitEvent(EventType.preinit);
        }catch (Throwable t){
            getLogger().log(Level.SEVERE,"[spigot-112] Will not enable plugin '"+ com.c0d3m4513r.plugindef.Plugin.name+"' with id '"+ com.c0d3m4513r.plugindef.Plugin.id+"' because of the following error:",t);
            super.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        EventRegistrar.submitEvent(EventType.commandRegister);
        EventRegistrar.submitEvent(EventType.init);
        //Idea: The server will only start ticking, once everything is loaded.
        TaskBuilder.builder().reset().executer(()-> EventRegistrar.submitEvent(EventType.load_complete)).build();
    }

    @Override
    public <T> @Nullable T loadConfigKey(String path, Class<T> type) {
        try {
            FileConfiguration config = getConfig();
            return type.cast(config.get(path));
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")//Either it works, or we catch the ClassCastException.
    public @Nullable <T> List<T> loadConfigKeyList(String path, Class<T> type) {
        try {
            FileConfiguration config = getConfig();
            return (List<T>) config.getList(path);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public <T> boolean saveConfigKey(@Nullable T value, @lombok.NonNull Class<T> typeToken, @lombok.NonNull String path) {
        try {
            FileConfiguration config = getConfig();
            config.set(path,value);
            return true;
        } catch (IllegalArgumentException | IllegalStateException | ClassCastException e) {
            return false;
        }
    }

    @Override
    public <V, T> boolean saveConfigKeyList(@Nullable V value, @NonNull Class<T> typeToken, @NonNull String path) {
        return false;
    }

    @Override
    public boolean updateConfigLoader() {
        reloadConfig();
        return true;
    }
}
