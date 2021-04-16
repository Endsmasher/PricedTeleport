package de.endsmasher.pricedteleport.utils;


import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class ItemUtils {

        /** Creates a stained_glass_pane with the desired color and without a name
         * @param color - MetaData of stained glass pane
         */
        public static ItemStack spacer(GlassColor color) {

            ItemStack platzhalter = new ItemStack(Material.valueOf(color.toString() + "_STAINED_GLASS_PANE"), 1);

            ItemMeta meta = (ItemMeta) platzhalter.getItemMeta();
            assert meta != null;
            meta.setDisplayName(ChatColor.GRAY+" ");

            platzhalter.setItemMeta(meta);

            return platzhalter;
        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Put in null to keep empty. Use addLore Method to add further lines
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore)
        {
            ItemStack i = new ItemStack(mat,1);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        public static ItemStack createItem(Material material, String name, ArrayList<String> lore) {
            ItemStack i = new ItemStack(material,1);
            ItemMeta meta = i.getItemMeta();
            assert meta != null;
            meta.setDisplayName(name);
            if(lore != null) {
                meta.setLore(lore);
            }
            i.setItemMeta(meta);
            return i;
        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore, MetaData
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param b - MetaData as a byte
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore, byte b)
        {
            ItemStack i = new ItemStack(mat, 1, b);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore, MetaData
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param s - MetaData as a short
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore, short s)
        {
            ItemStack i = new ItemStack(mat, 1, s);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param unbreakable - should item be unbreakable
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore, boolean unbreakable)
        {
            ItemStack i = new ItemStack(mat,1);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            meta.setUnbreakable(unbreakable);
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param unbreakable - should item be unbreakable
         * @param durability - MetaData as a short
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore, boolean unbreakable, short durability)
        {
            ItemStack i = new ItemStack(mat,1);
            i.setDurability(durability);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            meta.setUnbreakable(unbreakable);
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore, Count
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param num - Amount of Items in stack
         * @return
         */
        public static ItemStack createItem(Material mat, String name, String lore, int num)
        {
            ItemStack i = new ItemStack(mat,num);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore, Count, MetaData
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param num - Amount of Items in stack
         * @param s - MetaData as a short
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore,int num, short s)
        {
            ItemStack i = new ItemStack(mat, num, s);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        /**Creates a new Item with the chosen Material, DisplayName, Lore, Enchantment
         * @param mat - Material of Item
         * @param name - Name of Item! DO: "" to make Default
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @param enchantment - Enchantment of Item
         * @param enchLvl - level of enchantment
         * @param hidden - should enchantment be shown
         * @return
         */
        public static ItemStack createItem(Material mat,String name,String lore, Enchantment enchantment, int enchLvl, boolean hidden)
        {
            ItemStack i = new ItemStack(mat,1);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            meta.addEnchant(enchantment, enchLvl, true);
            if(hidden)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            i.setItemMeta(meta);
            return i;

        }

        /**Create a skull of the given Player
         * @param p - Player who's skull should be created
         * @return
         */
        @SuppressWarnings("deprecation")
        public static ItemStack createSkull(Player p)
        {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(p.getName());
            meta.setDisplayName(p.getName());
            skull.setItemMeta(meta);
            return skull;
        }

        /**Create a skull of the given Player with a specific display name
         * @param name - name that will be displayed as Item name
         * @param p - Player who's skull should be created
         * @return
         */
        @SuppressWarnings("deprecation")
        public static ItemStack createSkull(String name,String p)
        {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(p);
            meta.setDisplayName(name);
            skull.setItemMeta(meta);
            return skull;
        }

        /**Create a skull of the given Player with a specific display name
         * @param name - name that will be displayed as Item name
         * @param p - Player who's skull should be created
         * @param lore - One lined Lore. Use addLore Method to add further lines
         * @return
         */
        @SuppressWarnings("deprecation")
        public static ItemStack createSkull(String name,String p, String lore)
        {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(p);
            meta.setDisplayName(name);
            if(lore != null && !lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            skull.setItemMeta(meta);
            return skull;
        }



        /**Add lore to ItemStack
         * @param item - Item lore should be added to
         * @param s - list of lines that will be added
         * @return
         */
        public static ItemStack addLore(ItemStack item, String... s) {
            ItemMeta meta = item.getItemMeta();

            if(s == null)
                return item;

            ArrayList<String> metalore = (ArrayList<String>) meta.getLore();

            if(metalore == null)
                metalore = new ArrayList<>();

            for(String line : s) {
                if(line != null)
                    metalore.add(line);
            }

            meta.setLore(metalore);

            item.setItemMeta(meta);
            return item;
        }

        /**Add lore to ItemStack
         * @param item - Item lore should be added to
         * @param s - list of lines that will be added
         * @return
         */
        public static ItemStack addLoreLineStart(ItemStack item, String startLine, String... s) {
            ItemMeta meta = item.getItemMeta();

            if(s == null)
                return item;

            ArrayList<String> metalore = (ArrayList<String>) meta.getLore();

            if(metalore == null)
                metalore = new ArrayList<>();

            for(String line : s) {
                if(line != null)
                    metalore.add(startLine + line);
            }

            meta.setLore(metalore);

            item.setItemMeta(meta);
            return item;
        }

        public static ItemStack addEnchantments(ItemStack item, Enchantment enchantment, int level, boolean shown) {
            ItemMeta meta = item.getItemMeta();

            if(enchantment == null)
                return item;

            ArrayList<String> metalore = (ArrayList<String>) meta.getLore();

            if(metalore == null)
                metalore = new ArrayList<>();

            meta.addEnchant(enchantment, level, true);
            if(!shown)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            else
                meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

            meta.setLore(metalore);

            item.setItemMeta(meta);
            return item;
        }

        public static ItemStack setName(ItemStack item, String string) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(string);
            item.setItemMeta(meta);
            return item;
        }

        public static ItemStack createPotion(boolean simple, PotionType type, int level, boolean extended, boolean upgraded, String displayName) {
            ItemStack potion;
            if(simple)
                potion = new ItemStack(Material.POTION, 1);
            else
                potion = new ItemStack(Material.SPLASH_POTION, 1);

            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            meta.setDisplayName(displayName);
            meta.setBasePotionData(new PotionData(type, extended, upgraded));
            potion.setItemMeta(meta);
            return potion;
        }

        public static ItemStack createItemLeatherArmour(Material mat,String name,String lore, Color color)
        {
            ItemStack i = new ItemStack(mat, 1);
            LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
            meta.setDisplayName(name);
            meta.setColor(color);
            if(!lore.equals("nichts")){
                ArrayList<String> metalore = new ArrayList<String>();
                metalore.add(lore);
                meta.setLore(metalore);
            }
            i.setItemMeta(meta);
            return i;

        }

        public static ItemStack clearLore(ItemStack item) {
            ItemMeta meta = item.getItemMeta();

            ArrayList<String> metalore = new ArrayList<>();
            meta.setLore(metalore);

            item.setItemMeta(meta);

            return item;
        }

        public static ItemStack makeUnbreakeable(ItemStack item) {
            ItemMeta meta = item.getItemMeta();

            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            item.setItemMeta(meta);

            return item;
        }

        public static ItemStack createGuiItem(String name, String lore, short id) {
            ItemStack itemStack = ItemUtils.createItem(Material.WOODEN_HOE, name, lore, true, id);
            ItemMeta meta = itemStack.getItemMeta();
            meta.addItemFlags(ItemFlag.values());
            itemStack.setItemMeta(meta);
            return itemStack;
        }

        public enum GlassColor {
            BLACK,
            BLUE,
            BROWN,
            CYAN,
            GRAY,
            GREEN,
            LIGHT_BLUE,
            LIGHT_GRAY,
            LIME,
            MAGENTA,
            ORANGE,
            PINK,
            PURPLE,
            RED,
            WHITE,
            YELLOW
        }
    }
