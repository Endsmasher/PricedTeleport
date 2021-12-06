package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.TeleportWarpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class TeleportWarpListener implements Listener {

    /**
     * teleport player to warp and remove the items needed to teleport him
     */

    @EventHandler
    public void onWarp(TeleportWarpEvent event) {
        var player = event.getPlayer();
        var navigator = event.getNavigatorLocations();
        var location = navigator.getLocation();

        if(!player.hasPermission("ptp.bypass")) {
            if (!navigator.getItemStacks().isEmpty()) {
                for (ItemStack itemStack : navigator.getItemStacks()) {

                    if (!player.getInventory().contains(itemStack)) {
                        player.sendMessage(PricedTeleport.PREFIX + "You don't meet the requirements");
                        break;
                    }
                }
                for (ItemStack itemStack : navigator.getItemStacks()) {
                    player.getInventory().remove(itemStack);
                }
            }
        }

        player.teleport(location);
        player.sendMessage(PricedTeleport.PREFIX + "You have been teleported to " + navigator.getName());
    }
}
