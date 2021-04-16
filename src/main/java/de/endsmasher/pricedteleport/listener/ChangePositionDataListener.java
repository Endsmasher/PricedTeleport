package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.ChangePositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import lombok.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Data
public class ChangePositionDataListener implements Listener {
    private final LocationManager locationManager;

    @EventHandler
    public void onChangePositionData(ChangePositionDataEvent event) {
        var player = event.getPlayer();
        var locations = event.getNavigatorLocations();

        if (locationManager.get(locations.getId()) != null) {
            locationManager.remove(locations.getId());
        }
        locationManager.add(locations);
        player.sendMessage(PricedTeleport.PREFIX + "Position has been updated");
    }
}