package com.github.beastyboo.data.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Bukkit's implementation of SnakeYAML used from FileConfiguration.
 *
 * Object should only require output on application hook-point load up, and therefore does not need async support.
 */
public class Config {

    private final File file;
    private final FileConfiguration fileConfiguration;

    public Config(File file, FileConfiguration fileConfiguration) {
        this.file = file;
        this.fileConfiguration = fileConfiguration;
    }

    @Deprecated
    /**
     * Get's the file. Behaviour should be moved into this class for proper encapsulation.
     */
    public File getFile() {
        return file;
    }

    @Deprecated
    /**
     * Get's the file. Behaviour should be moved into this class for proper encapsulation.
     */
    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public String getString(String path) {
        return fileConfiguration.getString(path);
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException ex) {
            throw new UncheckedIOException("Could not save config.yml ", ex);
        }
    }

}
