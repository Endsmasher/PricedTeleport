package de.endsmasher.pricedteleport.event;

import de.endsmasher.pricedteleport.model.location.NavigatorLocations;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

@EqualsAndHashCode(callSuper = true)
@Data
public class OpenPositionDataEvent extends PricedTeleportEvent{
    private final Player player;
    private final NavigatorLocations navigatorLocations;

    @Override
    public HandlerList getHandlers() {return HANDLERS_LIST;}

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
