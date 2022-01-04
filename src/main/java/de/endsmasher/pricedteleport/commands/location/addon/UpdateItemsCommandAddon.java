package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.OpenPositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateItemsCommandAddon extends CommandBase{

    /**
     * set items which are required to teleport to a specific warp
     */

    @Override
    public void handle(LocationManager locationManager, Player player, String[] args) {
        var name = args[1];
        var locations = locationManager.get(name, true);
        if (locations == null) {
            player.sendMessage(PricedTeleport.PREFIX + "This position does not exist");
        } else {
            Bukkit.getPluginManager().callEvent(new OpenPositionDataEvent(player, locations));
        }
    }
}
