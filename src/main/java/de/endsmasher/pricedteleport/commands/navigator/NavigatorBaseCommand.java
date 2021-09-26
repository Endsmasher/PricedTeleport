package de.endsmasher.pricedteleport.commands.navigator;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.commands.BasicCommand;
import de.endsmasher.pricedteleport.commands.navigator.addon.ListPriceAddon;
import de.endsmasher.pricedteleport.commands.navigator.addon.TeleportCommandAddon;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigatorBaseCommand extends BasicCommand {

    private final LocationManager locationManager;

    public NavigatorBaseCommand(PricedTeleport main, LocationManager locationManager) {
        super(main, "warp");
        this.locationManager = locationManager;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission("ptp.warp")) return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage(PricedTeleport.PREFIX + "You are not a player");
            return false;
        }
        var player = (Player) sender;

        if (!(args.length >= 1)) {
            return false;
        }
        var type = args[0];

        if (type.equals("price")) {
            if (!(args.length >= 2)) {
                player.sendMessage(PricedTeleport.PREFIX + "Please do /warp price <name>");
            } else {
                new ListPriceAddon(locationManager, args[1], player);
            }
        } else {
            new TeleportCommandAddon(locationManager, player, args[0]);
        }
        return true;
    }
}
