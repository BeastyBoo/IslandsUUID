package com.github.beastyboo.island;

import com.github.beastyboo.DataObject;
import com.github.beastyboo.IslandsImpl;
import com.google.inject.Inject;
import com.mongodb.client.model.Filters;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class IslandManager {

    private final IslandsImpl core;
    private final Map<UUID, Island> islands;

    @Inject
    public IslandManager(IslandsImpl core) {
        this.core = core;
        islands = new HashMap<>();
    }

    public Island toggleIslandTest(Player player) {
        UUID uuid = player.getUniqueId();
        if(islands.containsKey(uuid)) {
            deleteIsland(player);
            return null;
        } else {
            return createIsland(player);
        }
    }

    private Island createIsland(Player player) {
        Island island = new Island(player.getUniqueId());
        islands.putIfAbsent(island.getUuid(), island);
        player.sendMessage(Component.text("Island created!"));

        return island;
    }

    private void deleteIsland(Player player) {
        UUID uuid = player.getUniqueId();
        if(islands.containsKey(uuid)) {
            islands.remove(uuid);
            player.sendMessage(Component.text("Island deleted!"));
        }
    }

    public Island getIsland(UUID uuid) {
        return islands.get(uuid);
    }

    public Island getAccurateIsland(UUID uuid) {
        Future<Optional<? extends DataObject>> futureDataObject = core.getMongoCenter().getDataObject(Filters.eq("uuid", uuid.toString()), IslandCollection.ISLANDS);

        Optional<? extends DataObject> optionalDataObject;

        try {
            optionalDataObject = futureDataObject.get();
        } catch (InterruptedException | ExecutionException ignored) {
            return null;
        }

        if(optionalDataObject.isEmpty()) {
            return null;
        }

        DataObject dataObject = optionalDataObject.get();

        if(dataObject instanceof Island) {
            Island island = (Island) dataObject;
            return island;
        }
        return null;
    }

}
