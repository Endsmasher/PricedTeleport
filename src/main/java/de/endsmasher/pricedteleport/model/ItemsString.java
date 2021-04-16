package de.endsmasher.pricedteleport.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

@Data
public class ItemsString {
    private final String material;
    private String name;
    private final int amount;
    private List<String> lore;


    public ItemsString(ItemStack itemStack) {
        material = itemStack.getType().toString();
        name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        amount = itemStack.getAmount();
        lore = itemStack.getItemMeta().getLore();
    }
}
