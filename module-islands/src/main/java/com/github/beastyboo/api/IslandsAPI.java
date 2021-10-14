package com.github.beastyboo.api;

import com.github.beastyboo.island.Island;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IslandsAPI {

    Island toggleIslandTest(Player player);

    Island getIsland(UUID uuid);

}
