package de.endsmasher.pricedteleport.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.endsmasher.pricedteleport.PricedTeleport;
import de.endsmasher.pricedteleport.model.location.NavigatorLocations;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonLocationBackend implements LocationBackend {

    private final ObjectMapper objectMapper;

    public JsonLocationBackend() {this.objectMapper = new ObjectMapper();}


    @Override
    public void save(NavigatorLocations locations) {
        saveToJson(locations);
    }

    @Override
    public List<NavigatorLocations> load() {
        var folder = new File(PricedTeleport.getInstance().getDataFolder().getPath() + "/Positions");

        if (!folder.exists()) {
            folder.mkdirs();
            try {
                folder.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<NavigatorLocations> locations = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Path.of(folder.getPath()))) {
            paths.filter(Files::isRegularFile).forEach(files -> {
                var data = readFromJson(new File(String.valueOf(files)));

                if (data == null) return;
                locations.add(data);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locations;
    }

    @Override
    public void remove(String name) {
        File file = new File(PricedTeleport.getInstance().getDataFolder().getPath() + "/Positions", name);
        try {
            FileUtils.touch(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.exists()) return;
        FileUtils.deleteQuietly(file);
    }

    private NavigatorLocations readFromJson(File file) {

        NavigationPojo pojo = null;
        List<ItemStack> itemStackList = new ArrayList<>();

        try {
            pojo = objectMapper.readValue(file, NavigationPojo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pojo == null) {
            System.out.println("No positions set");
            return null;
        }

        NavigatorLocations locations = new NavigatorLocations(pojo.getId());
        locations.setName(pojo.getName());
        locations.setLocation(pojo.getLocation().toLocation());
        if (pojo.getItemStack() != null) {
            if (pojo.getItemStack().isEmpty()) return locations;
            pojo.getItemStack().forEach(itemPojo -> itemStackList.add(itemPojo.toItemStack()));
        }
        locations.setItemStacks(itemStackList);
        return locations;
    }

    private void saveToJson(NavigatorLocations locations) {
        var file = new File(PricedTeleport.getInstance().getDataFolder().getPath() + "/Positions", locations.getId() + ".json");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        var pojoLocation = createLocationEntry(locations);

        try {
            var jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoLocation);
            var stream = new FileOutputStream(file);

            stream.write(jsonString.getBytes());
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private NavigationPojo createLocationEntry(NavigatorLocations locations) {
        var navigationPojo = new NavigationPojo();
        var itemStacks = locations.getItemStacks();
        List<ItemPojo> itemPojos = new ArrayList<>();

        navigationPojo.setId(locations.getId());
        navigationPojo.setName(locations.getName());
        navigationPojo.setLocation(new LocationPojo(locations.getLocation()));

        if (itemStacks == null) return navigationPojo;
        itemStacks.forEach(itemStack -> itemPojos.add(new ItemPojo(itemStack)));

        navigationPojo.setItemStack(itemPojos);
        return navigationPojo;
    }


    @Data
    private static class NavigationPojo {

        private String id;
        private String name;
        private LocationPojo location;
        private ItemPojo icon;
        private List<ItemPojo> itemStack;

    }

    @Data
    @NoArgsConstructor
    private static class LocationPojo {

        private String world;
        private int x;
        private int y;
        private int z;

        public LocationPojo(Location location) {
            if (location.getWorld() == null) return;
            world = location.getWorld().getName();
            x = location.getBlockX();
            y = location.getBlockY();
            z = location.getBlockZ();
        }

        public Location toLocation() {
            return new Location(Bukkit.getWorld(world), x, y, z);
        }
    }

    @Data
    @NoArgsConstructor
    private static class ItemPojo {

        private String name;
        private String material;
        private int amount;
        private List<String> lore;

        public ItemPojo(ItemStack itemStack) {
            if (itemStack == null) return;
            var itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                name = itemMeta.getDisplayName();
                lore = itemMeta.getLore();
            }
            material = itemStack.getType().toString();
            amount = itemStack.getAmount();
        }

        public ItemStack toItemStack() {
            ItemStack itemStack = new ItemStack(Material.valueOf(material));
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemStack.setAmount(amount);
            if (itemMeta == null) return itemStack;
            itemMeta.setLore(lore);
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        }
    }
}
