package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.ChangePositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateNameCommandAddon {

    /**
     * change name for a already existing warp
     */

    public UpdateNameCommandAddon(LocationManager locationManager, Player player, String name, String newName) {
        var locations = locationManager.get(name, true);
        if (locations == null) {
            player.sendMessage(PricedTeleport.PREFIX + "This Location does not exist");
        } else {
            locations.setName(newName);
            Bukkit.getPluginManager().callEvent(new ChangePositionDataEvent(player, locations));
        }
    }
}
