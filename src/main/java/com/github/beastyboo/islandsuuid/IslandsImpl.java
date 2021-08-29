package com.github.beastyboo.islandsuuid;

import com.github.beastyboo.islandsuuid.api.IslandsAPI;
import com.github.beastyboo.islandsuuid.island.Island;
import com.github.beastyboo.islandsuuid.island.IslandManager;
import com.github.beastyboo.islandsuuid.module.IslandModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.logging.Logger;

public class IslandsImpl implements IslandsAPI {

    private final JavaPlugin plugin;
    private final Logger logger;

    private IslandManager islandManager;

    @Inject
    public IslandsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    void load() {
        logger.info("Starting up UUIDIslands");

        Injector injector = Guice.createInjector(new IslandModule(this));
        islandManager = injector.getInstance(IslandManager.class);
    }

    void close() {
        logger.info("Shutting down UUIDIslands");
    }

    @Override
    public Island createIsland() {
        return islandManager.createIsland();
    }

    @Override
    public boolean deleteIsland(UUID uuid) {
        return islandManager.deleteIsland(uuid);
    }

    @Override
    public Island getIsland(UUID uuid) {
        return islandManager.getIsland(uuid);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }
}
