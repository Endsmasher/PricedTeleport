package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.entity.Player;

public class RemovePositionCommandAddon extends CommandBase {


    /**
     * remove the target position with the given name
     */

    @Override
    public void handle(LocationManager locationManager, Player player, String[] args) {
        var name = args[1];
        if (locationManager.get(name, true) == null) {
            player.sendMessage(PricedTeleport.PREFIX + "No position with this name is set");
        } else {
            locationManager.remove(name, true);
            player.sendMessage(PricedTeleport.PREFIX + "Removed position");
        }
    }
}