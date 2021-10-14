package com.github.beastyboo.data.config;

import com.github.beastyboo.IslandsImpl;
import com.google.inject.Inject;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ConfigFactory {

    private final IslandsImpl core;

    @Inject
    public ConfigFactory(IslandsImpl core) {
        this.core = core;
    }

    public Config getConfig() {

        File file = new File(core.getPlugin().getDataFolder(), "config.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        if(file.getParentFile().mkdirs()) {
            core.getLogger().info("Directory created");
        }

        if(!file.exists()) {
            try {
                if(file.createNewFile()) {
                    core.getLogger().info("config.yml created");

                    configuration.set("mongo-uri", "mongodb://[username]:[password]@[host]:[port]/?authSource=admin");
                    configuration.set("database", "myDatabase");
                    configuration.save(file);
                }
            } catch (IOException ex) {
                throw new UncheckedIOException("Could not create config.yml", ex);
            }
        }

        return new Config(file, configuration);
    }
}
