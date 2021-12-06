package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.event.TeleportWarpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.*;
import java.util.stream.Collectors;

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
                if (!checkAndRemoveItems(player.getInventory(), navigator.getItemStacks())) {
                    player.sendMessage(PricedTeleport.PREFIX + "You don't meet the requirements");
                    return;
                }
            }
        }

        player.teleport(location);
        player.sendMessage(PricedTeleport.PREFIX + "You have been teleported to " + navigator.getName());
    }

    private boolean checkAndRemoveItems(PlayerInventory playerInventory, List<ItemStack> requiredItems) {
        var i = -1;
        var items = requiredItems.stream().collect(Collectors.toMap(itemStack -> itemStack, ItemStack::getAmount, Integer::sum, HashMap::new));
        var slotsToChange = new HashMap<Integer, Integer>();

        for (ItemStack itemStack : playerInventory.getContents()) {
            i++;
            if (itemStack == null) continue;
            for (var requiredItem : new ArrayList<>(items.entrySet())) {
                if (!itemsMatch(itemStack, requiredItem.getKey())) continue;
                var missing = requiredItem.getValue();
                missing -= slotsToChange.getOrDefault(i, itemStack.getAmount());
                if (missing < 0) {
                    items.remove(requiredItem.getKey());
                    slotsToChange.put(i, Math.abs(missing));
                    continue;
                }
                items.put(requiredItem.getKey(), missing);
                if (missing == 0) {
                    items.remove(requiredItem.getKey());
                }
                slotsToChange.put(i, 0);
                break;
            }
        }
        if (items.size() > 0) {
            return false;
        }
        slotsToChange.forEach((slot, amount) -> {
            if (amount <= 0) {
                playerInventory.setItem(slot, null);
                return;
            }
            Objects.requireNonNull(playerInventory.getItem(slot)).setAmount(amount);
        });
        return true;
    }

    private boolean itemsMatch(ItemStack itemStack, ItemStack requiredItem) {
        if (itemStack.getType() != requiredItem.getType()) return  false;
        if (!Objects.equals(extractName(itemStack), extractName(requiredItem))) return false;
        // todo meta stuff
        return true;
    }

    private String extractName(ItemStack itemStack) {
        var meta = itemStack.getItemMeta();
        if (meta == null) return null;
        if (!meta.hasDisplayName()) return null;
        return meta.getDisplayName();
    }

}
