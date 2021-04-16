package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class CloseInventoryListener implements Listener {

    private final LocationManager locationManager;
    private final PlayerManager playerManager;

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {
        var player = (Player) event.getPlayer();
        var uuid = player.getUniqueId();
        var pricedPlayer = playerManager.get(uuid);

        if (pricedPlayer == null) {
            return;
        }
        var inventory = pricedPlayer.getInventory();

        if (inventory == null) return;
        if (event.getInventory() != inventory) return;
        var navigator = pricedPlayer.getInvlocations();

        if (navigator == null) {
            return;
        }
        if (navigator.getItemStacks() == null) {
            navigator.setItemStacks(Collections.emptyList());
        }
        if (event.getInventory().isEmpty()) return;
        List<ItemStack> items = new ArrayList<>();

        event.getInventory().forEach(itemStack -> {
            if (itemStack != null) {
                items.add(itemStack);
            }
        });

        navigator.setItemStacks(items);
        locationManager.save(navigator);
        player.sendMessage(PricedTeleport.PREFIX + "Updated");
        playerManager.removeInventory(player.getUniqueId());
    }
}
