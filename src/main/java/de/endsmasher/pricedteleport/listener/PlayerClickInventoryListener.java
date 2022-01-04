package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.commands.navigator.addon.TeleportCommandAddon;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@Data
public class PlayerClickInventoryListener implements Listener {

    private final PlayerManager playerManager;
    private final LocationManager locationManager;

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        var player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != playerManager.get(player.getUniqueId()).getInventory()) return;
        if (!event.getView().getTitle().equals("Warps")) return;

        event.setCancelled(true);
        var item = event.getCurrentItem();
        if (item == null) return;
        if (item.getItemMeta() == null) return;
        new TeleportCommandAddon(locationManager, player, item.getItemMeta().getDisplayName());
    }
}
