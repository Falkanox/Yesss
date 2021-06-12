package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class InventoryClick implements Listener {

    private Bingo main;
    public InventoryClick(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(main.isState(GState.WAITING)){

            if(!p.isOp()){

                e.setCancelled(true);

            }

            if(e.getCurrentItem() == null) return;
            if(e.getCurrentItem().getItemMeta() == null) return;

            UUID uuid = p.getUniqueId();

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cÉquipe Rouge")){

                main.redTeam.addToRedTeam(p.getUniqueId(), p);

            }

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bÉquipe Bleue")){

                main.blueTeam.addToBlueTeam(p.getUniqueId(), p);

            }

        }

    }

}
