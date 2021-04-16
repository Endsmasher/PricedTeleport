package de.endsmasher.pricedteleport.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryView;

@EqualsAndHashCode(callSuper = true)
@Data
public class SetPositionDataEvent extends PricedTeleportEvent{

    private final Player player;
    private final InventoryView inventoryView;

    @Override
    public HandlerList getHandlers() {return HANDLERS_LIST;}

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
