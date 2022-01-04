package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class UpdateIconCommandAddon extends CommandBase {

    @Override
    public void handle(LocationManager locationManager, Player player, String[] args) {
        var name = args[1];
        if (locationManager.get(name, true) == null) {
            player.sendMessage(PricedTeleport.PREFIX + "No position with this name is set");
        } else {
            var location = locationManager.get(name, true);
            var item = player.getItemInHand();
            if (item.getType() == Material.AIR) {
                player.sendMessage(PricedTeleport.PREFIX + "Please put the target item in your hand");
                return;
            }
            locationManager.save(location);
            player.sendMessage(PricedTeleport.PREFIX + "Successful updated icon");
        }
    }
}
