package fr.plum.plumlib.gui;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


// To use this class, you have to instanciate it, 
// and add items to it with addItem() or setItem().
// The action a click does depends of the onClick() function of the added GUIItem. 
public final class GUIInventory {
    protected Inventory inv;

    protected final HashMap<String, GUIItem> items = new HashMap<String, GUIItem>();

    public GUIInventory() {
        inv = null;

    }

    public void newInventory(final int size, final String name) {
        items.clear();
        // Create a new inventory, with no owner (as this isn't a real inventory)
        inv = Bukkit.createInventory(null, (size - 1) / 9 * 9 + 9 /* get a multiple of 9 */, name);
    }

    public void clear() {
        items.clear();
        inv.clear();
    }

    public GUIInventory(final int size, final String name) {
        newInventory(size, name);
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        assert(inv != null); // You should call newInventory() before !
        ent.openInventory(inv);
    }

    public void click(final InventoryClickEvent e) {

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) 
            return;

        // // Using slots click is a best option for your inventory click's
        GUIItem item = items.get(e.getCurrentItem().getItemMeta().getDisplayName());
        item.onClick(e);
    }

    public boolean tryToClick(final InventoryClickEvent e) {
        if (e.getInventory() == inv) {
            click(e);
            return true;
        }
        else 
            return false;
    }

    public boolean tryToDrag(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
           e.setCancelled(true);
            return true;
        }
        else 
            return false;
    }

    public void addItem(final GUIItem item) {
        items.put(item.getUniqueID(), item);
        inv.addItem(item.itemStack);
    }

    public void setItem(final int index, final GUIItem item) {
        items.put(item.getUniqueID(), item);
        inv.setItem(index, item.itemStack);
    }
}