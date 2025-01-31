package dev.theancientowl.feathershowcase.modules.teleport.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.defaultybuf.feather.toolkit.api.FeatherCommand;
import dev.defaultybuf.feather.toolkit.core.Placeholder;
import dev.defaultybuf.feather.toolkit.util.java.Pair;
import dev.defaultybuf.feather.toolkit.util.java.StringUtils;
import dev.defaultybuf.feather.toolkit.util.parsing.Args;
import dev.theancientowl.feathershowcase.modules.teleport.interfaces.ITeleport;

public class StartTeleportCommand extends FeatherCommand<StartTeleportCommand.CommandData> {

    public StartTeleportCommand(InitData data) {
        super(data);
    }

    public static record CommandData(Player player, double x, double y, double z, World world) {
    }

    @Override
    protected boolean hasPermission(CommandSender sender, CommandData data) {
        return sender instanceof Player && sender.hasPermission("feather-showcase.teleport");
    }

    @Override
    protected void execute(CommandSender sender, CommandData data) {
        getInterface(ITeleport.class).startTeleport(data.player, data.x, data.y, data.z, data.world);
    }

    @Override
    protected CommandData parse(CommandSender sender, String[] args) {
        CommandData outData = null;

        var parsedArgs = Args.parse(args, Args::getDouble, Args::getDouble, Args::getDouble, Args::getWorld);

        if (parsedArgs.success()) {
            outData = new CommandData(
                    (Player) sender,
                    parsedArgs.getDouble(0),
                    parsedArgs.getDouble(1),
                    parsedArgs.getDouble(2),
                    parsedArgs.getWorld(3));
        } else {
            switch (parsedArgs.failIndex()) {
                case 0:
                case 1:
                case 2:
                    getLanguage().message(sender, "teleport.invalid-number",
                            Pair.of(Placeholder.STRING, args[parsedArgs.failIndex()]));
                    break;
                case 3:
                    getLanguage().message(sender, "teleport.invalid-world", Pair.of(Placeholder.STRING, args[3]));
                    break;
                default:
                    break;
            }
        }

        return outData;
    }

    @Override
    protected List<String> onTabComplete(String[] args) {
        List<String> completions = new ArrayList<>();

        switch (args.length) {
            case 1:
                completions.add("x");
                break;
            case 2:
                completions.add("y");
                break;
            case 3:
                completions.add("z");
                break;
            case 4:
                completions = StringUtils.filterStartingWith(StringUtils.getWorlds(), args[3]);
                break;
            default:
                break;
        }

        return completions;
    }

}
