package dev.theancientowl.feathershowcase.modules.teleport.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import dev.defaultybuf.feather.toolkit.api.FeatherModule;
import dev.defaultybuf.feather.toolkit.core.Placeholder;
import dev.defaultybuf.feather.toolkit.exceptions.FeatherSetupException;
import dev.defaultybuf.feather.toolkit.util.java.Clock;
import dev.defaultybuf.feather.toolkit.util.java.Pair;
import dev.theancientowl.feathershowcase.modules.teleport.interfaces.ITeleport;

public class TeleportModule extends FeatherModule implements ITeleport {

    public static final record TeleportData(Player player, Location location, long issuedTime) {
    }

    private Map<UUID, TeleportData> teleports = null;

    public TeleportModule(InitData data) {
        super(data);
        teleports = new HashMap<>();
    }

    @Override
    protected void onModuleEnable() throws FeatherSetupException {
        Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), new TeleportChecker(this), 0,
                getConfig().getTicks("wait-time"));
    }

    @Override
    public void startTeleport(Player player, double x, double y, double z, World world) {
        this.teleports.put(player.getUniqueId(),
                new TeleportData(player, new Location(world, x, y, z), Clock.currentTimeMillis()));
        getLanguage().message(player, "teleport.start-teleporting");
    }

    @Override
    public void cancelTeleport(Player player) {
        if (this.teleports.containsKey(player.getUniqueId())) {
            this.teleports.remove(player.getUniqueId());
            getLanguage().message(player, "teleport.teleport-cancelled");
        }
    }

    private void executeTeleport(TeleportData data) {
        data.player.teleport(data.location);

        getLanguage().message(data.player, "teleport.executed", List.of(
                Pair.of(Placeholder.X, data.location.getX()),
                Pair.of(Placeholder.Y, data.location.getY()),
                Pair.of(Placeholder.Z, data.location.getZ()),
                Pair.of(Placeholder.WORLD, data.location.getWorld().getName())));
    }

    private static class TeleportChecker implements Runnable {
        private final TeleportModule teleportModule;

        public TeleportChecker(TeleportModule teleportModule) {
            this.teleportModule = teleportModule;
        }

        @Override
        public void run() {
            final var currentTime = Clock.currentTimeMillis();
            final var teleportWaiting = this.teleportModule.getConfig().getMillis("wait-time");

            final var iterator = this.teleportModule.teleports.entrySet().iterator();
            while (iterator.hasNext()) {
                final var entry = iterator.next();
                if (entry.getValue().issuedTime + teleportWaiting < currentTime) {
                    Bukkit.getScheduler().runTask(this.teleportModule.getPlugin(), () -> {
                        this.teleportModule.executeTeleport(entry.getValue());
                    });
                    iterator.remove();
                }
            }
        }
    }

}
