package com.github.beastyboo;

import com.google.inject.Inject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

final class PlaceBlockListener implements Listener {

    private final IslandsImpl core;

    @Inject
    PlaceBlockListener(IslandsImpl core) {
        this.core = core;
    }

    @EventHandler
    public void onListener(BlockPlaceEvent event) {
        Material material = event.getBlock().getType();

        if(material != Material.EMERALD_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        core.toggleIslandTest(player);
    }
}
