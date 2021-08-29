package com.github.beastyboo.islandsuuid;

import com.github.beastyboo.islandsuuid.api.IslandsAPI;
import com.github.beastyboo.islandsuuid.api.IslandsPlugin;
import com.github.beastyboo.islandsuuid.module.CoreModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

public final class IslandsPluginImpl extends JavaPlugin implements IslandsPlugin {

    private IslandsImpl core;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new CoreModule(this));
        core = injector.getInstance(IslandsImpl.class);
        core.load();
    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }

    @Override
    public IslandsAPI getAPI() {
        return core;
    }
}
