package de.endsmasher.pricedteleport.commands.location.addon;

import de.endsmasher.pricedteleport.model.location.LocationManager;
import org.bukkit.entity.Player;

public abstract class CommandBase {


    public abstract void handle(LocationManager locationManager, Player player, String[] args);
}
