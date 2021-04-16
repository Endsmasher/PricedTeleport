package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.SetPositionDataEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import lombok.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;

@Data
public class SetPositionDataListener implements Listener {

    private final LocationManager locationManager;

    @EventHandler
    public void setPosition(SetPositionDataEvent event) {
        var inventory = event.getInventoryView();
        var player = event.getPlayer();
        var locations = locationManager.get(inventory.getTitle());

        Arrays.stream(inventory.getTopInventory().getContents()).forEach(locations::addItemStack);
        player.sendMessage(PricedTeleport.PREFIX + "Requirements updated for " + locations.getName());
    }

}
