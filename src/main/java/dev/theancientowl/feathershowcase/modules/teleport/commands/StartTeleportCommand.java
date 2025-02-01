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

/**
 * @brief Command responsible for creating teleport module data
 * @extends FeatherCommand in order to be managed by the toolkit
 */
public class StartTeleportCommand extends FeatherCommand<StartTeleportCommand.CommandData> {

    public StartTeleportCommand(InitData data) {
        super(data);
    }

    /**
     * @brief Data that is parsed from command args and then executed
     */
    public static record CommandData(Player player, double x, double y, double z, World world) {
    }

    /**
     * @brief Check if the sender can issue this command
     * @param sender Who issued the command: console / player
     * @param data   parsed by StartTeleportCommand::parse(CommandSender,
     *               String[])
     * @return true if the sender has permission to execute this command, false
     *         otherwise
     */
    @Override
    protected boolean hasPermission(CommandSender sender, CommandData data) {
        return sender instanceof Player && sender.hasPermission("feather-showcase.teleport");
    }

    /**
     * @brief Execute command data parsed by
     *        StartTeleportCommand::parse(CommandSender, String[])
     * @param sender Who issued the command: console / player
     * @param data   parsed by StartTeleportCommand::parse(CommandSender,
     *               String[])
     */
    @Override
    protected void execute(CommandSender sender, CommandData data) {
        // To access any other module, getInterface(Class) can be used.
        // Here TeleportModule was refistered as ITeleport,
        // hence we're using it this way to access module API:
        getInterface(ITeleport.class).startTeleport(data.player, data.x, data.y, data.z, data.world);
    }

    /**
     * @brief Parse command args from "/teleport x y z world"
     * @note For simplicity we're gonna assume that the player is alywas sending the
     *       correct number of arguments. In practice we should check it :)
     * @param sender Who issued the command: console / player
     * @param args   command args
     * @return parsed command data if parsing was succesful, null otherwise
     */
    @Override
    protected CommandData parse(CommandSender sender, String[] args) {
        CommandData outData = null;

        // Args.parse takes in the string arguments and a list of parsers corresponding
        // to each argument in order.
        // For this command we know that first 3 args should be of type Double, and the
        // 4th should be World.
        // You can pass any other method that takes a string and returns your specific
        // object.
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

    /**
     * @brief Return tab completions based on command args
     * @param args
     * @return List of tab completions
     */
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
