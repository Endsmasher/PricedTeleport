package de.endsmasher.pricedteleport.commands.location.addon;


import de.endsmasher.pricedteleport.event.ChangePositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.location.NavigatorLocations;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetCommandAddon extends CommandBase {

    /**
     * add a new warp
     */

    @Override
    public void handle(LocationManager locationManager, Player player, String[] args) {
        var name = args[1];
        var location = player.getLocation();
        var navigator = new NavigatorLocations(randomId(locationManager));
        navigator.setLocation(location);
        navigator.setName(name);

        locationManager.save(navigator);
        Bukkit.getPluginManager().callEvent(new ChangePositionDataEvent(player, navigator));
    }

    private String randomId(LocationManager locationManager) {
        var uuid = UUID.randomUUID().toString();
        var id = uuid.replace("-", "");

        if (locationManager.get(id) != null) randomId(locationManager);
        return id;
    }
}
