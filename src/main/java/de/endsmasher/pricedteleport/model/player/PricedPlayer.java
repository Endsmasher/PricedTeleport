package de.endsmasher.pricedteleport.model.player;

import de.endsmasher.pricedteleport.model.location.NavigatorLocations;
import lombok.Data;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@Data
public class PricedPlayer {

    private final UUID uuid;
    private Inventory inventory;
    private NavigatorLocations invlocations;
}