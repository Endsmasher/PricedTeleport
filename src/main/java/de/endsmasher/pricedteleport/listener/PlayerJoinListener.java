package de.endsmasher.pricedteleport.listener;

import de.endsmasher.pricedteleport.model.player.PlayerManager;
import lombok.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Data
public class PlayerJoinListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        playerManager.add(event.getPlayer().getUniqueId());
    }
}
