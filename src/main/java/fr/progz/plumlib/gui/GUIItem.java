package fr.progz.plumlib.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

    public String getUniqueID() {
        return itemStack.getItemMeta().getDisplayName();
    }
}