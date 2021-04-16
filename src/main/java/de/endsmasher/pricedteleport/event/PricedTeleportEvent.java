package de.endsmasher.pricedteleport.event;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PricedTeleportEvent extends Event {
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    public static HandlerList getHandlerList() {return HANDLERS_LIST;}
}
