package fr.progz.plumlib.gui;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

interface IClickable {
    public void onClick(InventoryClickEvent e);
}

// Item used for the GUIInventory.
// To use it, you have to inherit, implement IClickable, and add it to the GUIInventory.
// When the item is clicked (from the inventory), the run() function is triggered.
public abstract class GUIItem implements IClickable {
    public ItemStack itemStack;
    
    public GUIItem(ItemStack item) {
        this.itemStack = item;
    }

    public GUIItem(final Material material, final String name, final String... lore) {
        itemStack = new ItemStack(material, 1);
        final ItemMeta meta = itemStack.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        itemStack.setItemMeta(meta);
    }

    public String getUniqueID() {
        return itemStack.getItemMeta().getDisplayName();
    }

    // Nice little method to create a gui item with a custom name, and description
    public static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        return createGuiItem(material, name, Arrays.asList(lore));
    }

    // Nice little method to create a gui item with a custom name, and description
    public static ItemStack createGuiItem(final Material material, final String name, final List<String> lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
}