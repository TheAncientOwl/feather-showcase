package dev.theancientowl.feathershowcase.modules.dummy.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.defaultybuf.feather.toolkit.api.FeatherListener;
import dev.theancientowl.feathershowcase.modules.teleport.interfaces.ITeleport;

/**
 * @brief Listener responsible for canceling teleports
 *        if the player right clicks a red wool block :)
 * @extends FeatherListener in order to be managed by the toolkit
 */
public class PlayerRightClickRedWoolListener extends FeatherListener {

    public PlayerRightClickRedWoolListener(InitData data) {
        super(data);
    }

    /**
     * @brief Call TeleportModule API to cancel the teleport request
     *        when the player right clicks red wool block
     * @param event
     */
    @EventHandler
    void onPlayerRightClickRedWool(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block != null && block.getType() == Material.RED_WOOL) {
                getInterface(ITeleport.class).cancelTeleport(event.getPlayer());
            }
        }
    }

}
