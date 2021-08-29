package com.github.beastyboo.islandsuuid.api;

import com.github.beastyboo.islandsuuid.island.Island;

import java.util.UUID;

public interface IslandsAPI {

    Island createIsland();

    boolean deleteIsland(UUID uuid);

    Island getIsland(UUID uuid);

}
