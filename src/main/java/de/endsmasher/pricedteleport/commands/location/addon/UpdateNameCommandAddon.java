package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.ChangePositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateNameCommandAddon extends CommandBase{

    /**
     * change name for a already existing warp
     */

    @Override
    public void handle(LocationManager locationManager, Player player, String[] args) {
        var name = args[1];
        var newName = args[2];
        var locations = locationManager.get(name, true);
        if (locations == null) {
            player.sendMessage(PricedTeleport.PREFIX + "This Location does not exist");
        } else {
            locations.setName(newName);
            locationManager.save(locations);
            Bukkit.getPluginManager().callEvent(new ChangePositionDataEvent(player, locations));
        }
    }
}
