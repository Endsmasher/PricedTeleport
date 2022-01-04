package de.endsmasher.pricedteleport.commands.navigator;

import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.commands.BasicCommand;
import de.endsmasher.pricedteleport.inventories.WarpListInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigatorListWarpsCommand extends BasicCommand {

    private final WarpListInventory warpListInventory;

    public NavigatorListWarpsCommand(PricedTeleport main, WarpListInventory warpListInventory) {
        super(main, "warps");
        this.warpListInventory = warpListInventory;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission("ptp.warps")) {
            sender.sendMessage(PricedTeleport.PREFIX + "You do not have the permission to do this");
            return false;
        }
        if (!(sender instanceof Player player)) {
            sender.sendMessage(PricedTeleport.PREFIX + "You are not a player");
        } else {
            warpListInventory.openInv(player);
        }
        return true;
    }
}
