package de.endsmasher.pricedteleport.commands;

import de.endsmasher.pricedteleport.PricedTeleport;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;

public abstract class BasicCommand extends BukkitCommand {
    protected PricedTeleport plugin;

    public BasicCommand(PricedTeleport main, String name, String permission) {
        super(name);
        setPermission(permission);
        plugin = main;
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(name, this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public BasicCommand(PricedTeleport main, String name) {
        this(main, name,"");
    }

    @Override
    public abstract boolean execute(CommandSender sender, String alias, String[] args);

}
