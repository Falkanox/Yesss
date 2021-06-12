package fr.falkanox.bingo.inventorys;

import fr.falkanox.bingo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamInventory {

    public void genInventory(Player p){

        Inventory inv = Bukkit.createInventory(null, 27, "Choisissez votre équipe !");

        ItemBuilder red = new ItemBuilder(Material.RED_WOOL).setDisplayName("§cÉquipe Rouge");
        ItemBuilder blue = new ItemBuilder(Material.BLUE_WOOL).setDisplayName("§bÉquipe Bleue");
        ItemBuilder glass = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§c");

        inv.setItem(11, red.build());
        inv.setItem(15, blue.build());

        for(int i = 0; i <= 10; i++){
            inv.setItem(i, glass.build());
        }

        for(int j = 12; j <= 14; j++){
            inv.setItem(j, glass.build());
        }

        for(int k = 16; k <= 26; k++){
            inv.setItem(k, glass.build());
        }

        p.openInventory(inv);

    }

}
