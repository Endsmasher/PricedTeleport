package de.endsmasher.pricedteleport;

import de.endsmasher.pricedteleport.backend.JsonLocationBackend;
import de.endsmasher.pricedteleport.backend.LocationBackend;
import de.endsmasher.pricedteleport.commands.location.LocationBaseCommand;
import de.endsmasher.pricedteleport.commands.navigator.NavigatorBaseCommand;
import de.endsmasher.pricedteleport.commands.navigator.NavigatorListWarpsCommand;
import de.endsmasher.pricedteleport.listener.*;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import de.endsmasher.pricedteleport.utils.ConfigHolder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PricedTeleport extends JavaPlugin {

    public static String PREFIX = "§7[§bPTP§7] §r";
    private static PricedTeleport instance;
    public static PricedTeleport getInstance() {return instance;}

    private ConfigHolder configHolder;
    private LocationBackend locationBackend;
    private LocationManager locationManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {

        instance = this;

        locationBackend = new JsonLocationBackend();
        configHolder = new ConfigHolder(this);
        locationManager = new LocationManager(locationBackend);
        playerManager = new PlayerManager();

        loadPositions();
        loadCommands();
        loadListeners();
    }

    private void loadCommands() {
        new LocationBaseCommand(this, locationManager);
        new NavigatorBaseCommand(this, locationManager);
        new NavigatorListWarpsCommand(this, locationManager);
    }
    private void loadListeners() {
        var pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ChangePositionDataListener(locationManager), this);
        pluginManager.registerEvents(new SetPositionDataListener(locationManager), this);
        pluginManager.registerEvents(new TeleportWarpListener(), this);
        pluginManager.registerEvents(new OpenPositionDataListener(playerManager), this);
        pluginManager.registerEvents(new CloseInventoryListener(locationManager, playerManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(playerManager), this);
        pluginManager.registerEvents(new PlayerDisconnectListener(playerManager), this);
    }

    private void loadPositions() {
        locationManager.setArrayList(locationBackend.load());
    }
    public ConfigHolder getConfigHolder() {return configHolder;}
}
