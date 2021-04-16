package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.event.OpenPositionDataEvent;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@Data
public class OpenPositionDataListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler(ignoreCancelled = true)
    public void onOpenPositionData(OpenPositionDataEvent event) {
        var inventory = Bukkit.createInventory(null, 9*3, event.getNavigatorLocations().getName());
        var optItems = event.getNavigatorLocations().getItemStacks();

        if (optItems != null) {
            if (!optItems.isEmpty()) {
                ItemStack[] itemStacks = optItems.toArray(new ItemStack[0]);
                inventory.setContents(itemStacks);
            }
        }
        playerManager.setInventory(event.getPlayer().getUniqueId(), inventory, event.getNavigatorLocations());
        event.getPlayer().openInventory(inventory);
    }
}