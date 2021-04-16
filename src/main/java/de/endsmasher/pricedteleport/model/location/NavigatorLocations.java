package de.endsmasher.pricedteleport.model.location;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class NavigatorLocations {

    private final String id;
    private String name;
    private Location location;
    private List<ItemStack> itemStacks;

    public void addItemStack(ItemStack itemStack) {itemStacks.add(itemStack);}
}
