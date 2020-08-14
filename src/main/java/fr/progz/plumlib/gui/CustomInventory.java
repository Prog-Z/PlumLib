package fr.progz.plumlib.gui;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// To use this class, you have to inherit it and override initializeItems() and onClick() :
// With initializeItems(), you add the items you want to show
// With onClick(), you select the action your run depending of the slot
public abstract class CustomInventory {
    protected Inventory inv;

    public CustomInventory() {
        inv = null;

    }

    public void newInventory(int size, String name) {
        // Create a new inventory, with no owner (as this isn't a real inventory)
        inv = Bukkit.createInventory(null, (size - 1) / 9 * 9 + 9 /* get a multiple of 9 */, name);
    }

    public CustomInventory(int size, String name) {
        newInventory(size, name);

        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public abstract void initializeItems();
    // public void initializeItems() {
    //     inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "Example Sword", "§aFirst line of the lore", "§bSecond line of the lore"));
    //     inv.addItem(createGuiItem(Material.IRON_HELMET, "§bExample Helmet", "§aFirst line of the lore", "§bSecond line of the lore"));
    // }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        return createGuiItem(material, name, Arrays.asList(lore));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final List<String> lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
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
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // final Player p = (Player) e.getWhoClicked();

        // // Using slots click is a best option for your inventory click's
        // p.sendMessage("You clicked at slot " + e.getRawSlot());
        onClick(e);
    }

    public boolean tryToClick(final InventoryClickEvent e) {
        if (e.getInventory() == inv) {
            click(e);
            return true;
        }
        else 
            return false;
    }

    public abstract void onClick(final InventoryClickEvent e);

    public void drag(final InventoryDragEvent e) {
        e.setCancelled(true);
    }

    public boolean tryToDrag(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            drag(e);
            return true;
        }
        else 
            return false;
    }
}