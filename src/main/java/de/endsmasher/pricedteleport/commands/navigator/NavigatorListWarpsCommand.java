package de.endsmasher.pricedteleport.commands.navigator;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.commands.BasicCommand;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigatorListWarpsCommand extends BasicCommand {

    private final LocationManager locationManager;

    public NavigatorListWarpsCommand(PricedTeleport main, LocationManager locationManager) {
        super(main, "warps");
        this.locationManager = locationManager;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission("ptp.warps")) return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage(PricedTeleport.PREFIX + "You are not a player");
        } else {
            var player = (Player) sender;
            var string = String.valueOf(locationManager.getNameList())
                    .replace("[", "")
                    .replace("]", "");

            player.sendMessage(PricedTeleport.PREFIX + "Available warps: " + string);
        }
        return true;
    }
}
