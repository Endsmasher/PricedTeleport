package de.endsmasher.pricedteleport.commands.navigator.addon;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.model.ItemsString;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ListPriceAddon {

    /**
     * show all item requirements needed to teleport to the target warp
     */

    public ListPriceAddon(LocationManager locationManager, String name, Player player) {
        var navigator = locationManager.get(name, true);
        if (navigator == null) return;

        var optItems = navigator.getItemStacks();

        if (optItems == null || optItems.isEmpty()) {
            player.sendMessage(PricedTeleport.PREFIX + "No requirements have been set");
            return;
        }
        Map<String, Material> items = new HashMap<>();
        AtomicReference<ItemsString> itemss = new AtomicReference<>();

        optItems.forEach(itemStack -> {
            if (itemStack.getItemMeta() != null) {
                items.put(itemStack.getItemMeta().getDisplayName(), itemStack.getType());
                itemss.set(new ItemsString(itemStack));
            }
        });
        if (items.isEmpty()) {
            player.sendMessage(PricedTeleport.PREFIX + "No requirements have been set");
        } else {

            player.sendMessage(PricedTeleport.PREFIX + itemss.get());
        }
    }
}
