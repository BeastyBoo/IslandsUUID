package com.github.beastyboo.islandsuuid.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreModule extends AbstractModule {

    private final JavaPlugin plugin;

    public CoreModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Provides
    public JavaPlugin providedPlugin() {
        return plugin;
    }
}
