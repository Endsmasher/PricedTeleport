package de.endsmasher.pricedteleport.inventories;

import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Data
public class WarpListInventory {

    private final LocationManager locationManager;
    private final PlayerManager playerManager;
    private int x = 0;

    public void openInv(Player player) {
        var inv = Bukkit.createInventory(null, 6*9, "Warps");

        locationManager.getLocations().forEach(navigatorLocations -> {
            inv.setItem(x, new ItemStack(Material.GRASS_BLOCK));
            x = x+1;
        });

        player.openInventory(inv);
        playerManager.get(player.getUniqueId()).setInventory(inv);
        x = 0;
    }
}
