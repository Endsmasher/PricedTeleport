package de.endsmasher.pricedteleport;

import de.endsmasher.pricedteleport.backend.JsonLocationBackend;
import de.endsmasher.pricedteleport.backend.LocationBackend;
import de.endsmasher.pricedteleport.commands.location.LocationBaseCommand;
import de.endsmasher.pricedteleport.commands.location.addon.*;
import de.endsmasher.pricedteleport.commands.navigator.NavigatorBaseCommand;
import de.endsmasher.pricedteleport.commands.navigator.NavigatorListWarpsCommand;
import de.endsmasher.pricedteleport.inventories.WarpListInventory;
import de.endsmasher.pricedteleport.listener.*;
import de.endsmasher.pricedteleport.model.location.LocationManager;
import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PricedTeleport extends JavaPlugin {

    public static String PREFIX = "§7[§bPTP§7] §r";
    private static PricedTeleport instance;
    public static PricedTeleport getInstance() {return instance;}

     LocationBackend locationBackend;
    private LocationManager locationManager;
    private PlayerManager playerManager;
    private AddonManager addonManager;
    private WarpListInventory warpListInventory;

    @Override
    public void onEnable() {

        instance = this;

        locationBackend = new JsonLocationBackend();
        locationManager = new LocationManager(locationBackend);
        playerManager = new PlayerManager();
        addonManager = new AddonManager();
        warpListInventory = new WarpListInventory(locationManager, playerManager);

        loadPositions();
        loadCommands();
        registerCommandAddons();
        loadListeners();
    }

    private void loadCommands() {
        new LocationBaseCommand(this, locationManager, addonManager);
        new NavigatorBaseCommand(this, locationManager);
        new NavigatorListWarpsCommand(this, warpListInventory);
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
        pluginManager.registerEvents(new PlayerClickInventoryListener(playerManager, locationManager), this);

    }

    private void registerCommandAddons() {

        addonManager.add("set", new SetCommandAddon());
        addonManager.add("name", new UpdateNameCommandAddon());
        addonManager.add("items", new UpdateItemsCommandAddon());
        addonManager.add("location", new UpdateLocationCommandAddon());
        addonManager.add("remove", new RemovePositionCommandAddon());
        //addonManager.add("icon", new UpdateIconCommandAddon());
    }

    private void loadPositions() {
        locationManager.setArrayList(locationBackend.load());
    }
}
