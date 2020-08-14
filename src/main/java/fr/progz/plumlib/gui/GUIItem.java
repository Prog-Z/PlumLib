package fr.progz.plumlib.gui;

import org.bukkit.inventory.ItemStack;

// Item used for the GUIInventory.
// To use it, you have to inherit, implement Runnable, and add it to the GUIInventory.
// When the item is clicked (from the inventory), the run() function is triggered.
public abstract class GUIItem implements Runnable {
    public ItemStack itemStack;
    
    public GUIItem(ItemStack item) {
        this.itemStack = item;
    }

    public String getUniqueID() {
        return itemStack.getItemMeta().getDisplayName();
    }
}