package de.endsmasher.pricedteleport.commands.location;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.commands.BasicCommand;
import de.endsmasher.pricedteleport.commands.location.addon.*;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LocationBaseCommand extends BasicCommand {

    private final LocationManager locationManager;

    public LocationBaseCommand(PricedTeleport main, LocationManager locationManager) {
        super(main, "ptp");
        this.locationManager = locationManager;
    }

    private static final String[] subCommands = {"\n-> set",
            "\n-> name",
            "\n-> location",
            "\n-> icon ",
            "\n-> items",
            "\n-> remove"};

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!sender.hasPermission("ptp.admin")) return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage(PricedTeleport.PREFIX + "You are not a player");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(PricedTeleport.PREFIX + "Available commands: §7" + Arrays.toString(subCommands)
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
            );
            return false;
        }
        if (args.length <= 1) {
            sender.sendMessage(PricedTeleport.PREFIX + "Please do /ptp <subcommand> <name>");
            return false;
        }

        var player = (Player) sender;
        var type = args[0];
        var name = args[1];

        switch (type) {
            case "set": {
                new SetCommandAddon(locationManager, player, name);
                break;
            }
            case "name": {
                if (args.length != 3) {
                    player.sendMessage(PricedTeleport.PREFIX + "Please do /ptp name <name> <newName>");
                    break;
                }
                var newName = args[2];
                new UpdateNameCommandAddon(locationManager, player, name, newName);
                break;
            }
            case "location": {
                new UpdateLocationCommandAddon(locationManager, player, name);
                break;
            }
            case "items": {
                new UpdateItemsCommandAddon(locationManager, player, name);
                break;
            }
            case "remove": {
                new RemovePositionCommandAddon(locationManager, player, name);
                break;
            }
        }
        return false;
    }
}
