package de.endsmasher.pricedteleport.model.player;

import de.endsmasher.pricedteleport.model.location.NavigatorLocations;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private final List<PricedPlayer> players = new ArrayList<>();


    public void add(UUID uuid) {
        var player = new PricedPlayer(uuid);
        if (players.contains(player)) return;
        players.add(new PricedPlayer(uuid));
    }

    public void setInventory(UUID uuid, Inventory inventory, NavigatorLocations invlocations) {
        for (PricedPlayer pricedPlayer : players) {
            if (pricedPlayer.getUuid() == uuid) {
                pricedPlayer.setInventory(inventory);
                pricedPlayer.setInvlocations(invlocations);
            }
        }
    }

    public PricedPlayer get(UUID uuid) {
        for (PricedPlayer pricedPlayer : players) {
            if (pricedPlayer.getUuid() == uuid) {
                return pricedPlayer;
            }
        }
        return null;
    }

    public void remove(UUID uuid) {
        var player = new PricedPlayer(uuid);
        players.remove(player);
    }

    public void replace(PricedPlayer old, PricedPlayer newPlayer) {
        players.set(getIndex(old), newPlayer);
    }

    private int getIndex(PricedPlayer pricedPlayer) {
        return players.indexOf(pricedPlayer);
    }

    public void removeInventory(UUID uuid) {
        get(uuid).setInventory(null);
    }
}
