package io.github.alvinnorin.agar;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

public class SampleBlockListener
implements Listener {
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block above;
        Block block = event.getBlock();
        if ((block.getType() == Material.SAND || block.getType() == Material.GRAVEL) && (above = block.getRelative(BlockFace.UP)).getType() == Material.IRON_BLOCK) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        Material mat = event.getMaterial();
        if (mat.equals((Object)Material.CACTUS)) {
            event.setBuildable(true);
        }
    }
}