package com.github.beastyboo;

import com.github.beastyboo.api.IslandsAPI;
import com.github.beastyboo.connection.MongoPoolFactory;
import com.github.beastyboo.connection.MongoPool;
import com.github.beastyboo.data.config.Config;
import com.github.beastyboo.data.config.ConfigFactory;
import com.github.beastyboo.impl.MongoCenterImpl;
import com.github.beastyboo.island.Island;
import com.github.beastyboo.island.IslandManager;
import com.github.beastyboo.module.IslandModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mongodb.client.MongoClient;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.logging.Logger;

public class IslandsImpl implements IslandsAPI {

    private final JavaPlugin plugin;
    private final Logger logger;

    private IslandManager islandManager;
    private Config config;
    private MongoClient client;
    private MongoCenter mongoCenter;

    @Inject
    public IslandsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    void load() {
        logger.info("Starting up UUIDIslands");

        Injector injector = Guice.createInjector(new IslandModule(this));
        islandManager = injector.getInstance(IslandManager.class);

        ConfigFactory configFactory = injector.getInstance(ConfigFactory.class);
        config = configFactory.getConfig();

        MongoPoolFactory mongoPoolFactory = new MongoPoolFactory(logger, config.getString("mongo-uri"), config.getString("database"));
        MongoPool mongoPool = mongoPoolFactory.startConnection();
        mongoCenter = new MongoCenterImpl(mongoPool, 3);

        plugin.getServer().getPluginManager().registerEvents(injector.getInstance(PlaceBlockListener.class), plugin);

    }

    void close() {
        logger.info("Shutting down UUIDIslands");
    }

    @Override
    public Island toggleIslandTest(Player player) {
        return islandManager.toggleIslandTest(player);
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

    public Config getConfig() {
        return config;
    }

    public MongoCenter getMongoCenter() {
        return mongoCenter;
    }

    public MongoClient getClient() {
        return client;
    }
}
