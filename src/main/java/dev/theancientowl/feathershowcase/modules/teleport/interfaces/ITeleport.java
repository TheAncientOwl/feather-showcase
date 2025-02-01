/**
 * ------------------------------------------------------------------------------ *
 *                     Copyright (c) by FeatherShowcase 2025                      *
 * ------------------------------------------------------------------------------ *
 * @license https://github.com/TheAncientOwl/feather-showcase/blob/main/LICENSE
 *
 * @file ITeleport.java
 * @author Alexandru Delegeanu
 * @version 0.1
 * @description TeleportModule interface
 */

package dev.theancientowl.feathershowcase.modules.teleport.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * @brief TeleportModule interface
 */
public interface ITeleport {
    public void startTeleport(Player player, double x, double y, double z, World world);

    public void cancelTeleport(Player player);

}
