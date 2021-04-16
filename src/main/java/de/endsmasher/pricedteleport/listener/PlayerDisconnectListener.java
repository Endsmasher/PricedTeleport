package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Data
public class PlayerDisconnectListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerManager.remove(event.getPlayer().getUniqueId());
    }
}
