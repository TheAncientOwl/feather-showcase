package dev.theancientowl.feathershowcase.modules.teleport.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;

public interface ITeleport {
    public void startTeleport(Player player, double x, double y, double z, World world);

    public void cancelTeleport(Player player);

}
