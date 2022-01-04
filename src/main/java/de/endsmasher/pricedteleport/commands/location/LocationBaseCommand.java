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
    private final AddonManager addonManager;

    public LocationBaseCommand(PricedTeleport main, LocationManager locationManager, AddonManager addonManager) {
        super(main, "ptp");
        this.locationManager = locationManager;
        this.addonManager = addonManager;
    }

    private static final String[] subCommands = {"\n-> set",
            "\n-> name",
            "\n-> location",
            "\n-> items",
            "\n-> remove"};

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!sender.hasPermission("ptp.admin")) {
            sender.sendMessage(PricedTeleport.PREFIX + "You do not have the permission to do this");
            return false;
        }
        if (!(sender instanceof Player player)) {
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

        if (args[0].equals("name") && args.length != 2) {
            sender.sendMessage(PricedTeleport.PREFIX + "Please do /ptp name <name> <newName>");
            return false;
        }

        try {
            addonManager.get(args[0]).handle(locationManager, player, args);
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("You added to less or to much words");
        }
        return false;
    }
}
