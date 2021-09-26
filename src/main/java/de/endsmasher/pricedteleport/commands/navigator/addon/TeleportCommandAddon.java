package de.endsmasher.pricedteleport.commands.navigator.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.TeleportWarpEvent;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportCommandAddon {

    /**
     * teleport to the target warp
     */

    public TeleportCommandAddon(LocationManager locationManager, Player player, String name) {
        var navigator = locationManager.get(name, true);
        if (navigator == null) {
            player.sendMessage(PricedTeleport.PREFIX + "Invalid location");
        } else {
            Bukkit.getPluginManager().callEvent(new TeleportWarpEvent(player, navigator));
        }
    }
}
