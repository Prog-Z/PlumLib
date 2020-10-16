package fr.plum.plumlib.Serialization;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.plum.plumlib.gui.CustomInventory;

public class SerializedVariablesGUI extends CustomInventory {
    public ArrayList<SerializedObject<Location>> serializedLocGetter = null;
    public ArrayList<SerializedObject<Boolean>> serializedBoolGetter = null;

    public SerializedVariablesGUI(ArrayList<SerializedObject<Location>> coords, 
                                  ArrayList<SerializedObject<Boolean>> booleans,
                                  String name) {
        assert(coords != null && booleans != null); // Should not be null

        serializedLocGetter = coords;
        serializedBoolGetter = booleans;

        newInventory(coords.size() + booleans.size(), name);
        initializeItems();
    }

    public void setupVariables(Player p, int varIndex) {
        if (varIndex < serializedLocGetter.size()) {
            serializedLocGetter.get(varIndex).value = p.getLocation();
        }
        else {
            int varIndexBool = varIndex - serializedBoolGetter.size();
            serializedBoolGetter.get(varIndexBool).value = !serializedBoolGetter.get(varIndexBool).value;
        }

        refresh();
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        setupVariables((Player) e.getWhoClicked(), e.getRawSlot());
    }

    @Override
    public void initializeItems() {
        for (SerializedObject<Location> coord : serializedLocGetter) {
            if (coord.value == null) {
                inv.addItem(createGuiItem(Material.LIGHT_BLUE_WOOL, coord.name, 
                    "null", 
                    coord.description));
            }
            else {
                inv.addItem(createGuiItem(Material.BLUE_WOOL, coord.name, 
                    coord.value.getBlockX() + " / " + coord.value.getBlockY() + " / "  + coord.value.getBlockZ(), 
                    coord.description));
            }
        }

        for (SerializedObject<Boolean> bool : serializedBoolGetter) {
            if (bool.value == null) {
                inv.addItem(createGuiItem(Material.GRAY_WOOL, bool.name,  "null", bool.description));
            }
            else if (bool.value) {
                inv.addItem(createGuiItem(Material.GREEN_WOOL, bool.name,  "true", bool.description));
            }
            else {
                inv.addItem(createGuiItem(Material.RED_WOOL, bool.name, "false", bool.description));
            }
        }
    }

    public void clear() {
        inv.clear();
    }

    public void refresh() {
        clear();
        initializeItems();
    }

    @Override
    public void openInventory(final HumanEntity ent) {
        refresh();
        super.openInventory(ent);
    }   
}