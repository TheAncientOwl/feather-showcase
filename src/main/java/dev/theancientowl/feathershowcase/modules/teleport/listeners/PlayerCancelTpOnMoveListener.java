package dev.theancientowl.feathershowcase.modules.teleport.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import dev.defaultybuf.feather.toolkit.api.FeatherListener;
import dev.theancientowl.feathershowcase.modules.teleport.interfaces.ITeleport;

/**
 * @brief Command responsible for canceling teleports if the player moves.
 * @extends FeatherListener in order to be managed by the toolkit
 */
public class PlayerCancelTpOnMoveListener extends FeatherListener {

    public PlayerCancelTpOnMoveListener(InitData data) {
        super(data);
    }

    /**
     * @brief When the player moves, call module API to cancel the teleport
     * @param event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) {
            return;
        }

        // To access any other module, getInterface(Class) can be used.
        // Here TeleportModule was refistered as ITeleport,
        // hence we're using it this way to access module API:
        getInterface(ITeleport.class).cancelTeleport(event.getPlayer());
    }

}
