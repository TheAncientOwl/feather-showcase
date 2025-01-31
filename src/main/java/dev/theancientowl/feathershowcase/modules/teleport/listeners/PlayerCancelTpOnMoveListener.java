package dev.theancientowl.feathershowcase.modules.teleport.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import dev.defaultybuf.feather.toolkit.api.FeatherListener;
import dev.theancientowl.feathershowcase.modules.teleport.interfaces.ITeleport;

public class PlayerCancelTpOnMoveListener extends FeatherListener {

    public PlayerCancelTpOnMoveListener(InitData data) {
        super(data);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) {
            return;
        }

        getInterface(ITeleport.class).cancelTeleport(event.getPlayer());
    }

}
