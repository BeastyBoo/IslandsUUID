package com.github.beastyboo.islandsuuid.island;

import com.github.beastyboo.islandsuuid.IslandsImpl;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandManager {

    private final IslandsImpl core;
    private final Map<UUID, Island> islands;

    @Inject
    public IslandManager(IslandsImpl core) {
        this.core = core;
        islands = new HashMap<>();
    }

    public Island createIsland() {
        Island island = new Island();
        islands.putIfAbsent(island.getUuid(), island);
        return island;
    }

    public boolean deleteIsland(UUID uuid) {
        if(islands.containsKey(uuid)) {
            islands.remove(uuid);

            return true;
        } else {
            return false;
        }
    }

    public Island getIsland(UUID uuid) {
        return islands.get(uuid);
    }
}
