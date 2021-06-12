package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    private Bingo main;
    public PlayerInteract(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();

        if(main.isState(GState.WAITING)){

            if(!p.isOp()){

               if(e.getItem() == null) return;

               if(e.getItem().hasItemMeta()){

                   if(e.getItem().getType() == Material.CLOCK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aChoisissez votre équipe !")){

                       if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){

                           if(!p.isOp()){

                                main.teamInventory.genInventory(p);

                           } else {

                               p.sendMessage(main.error + "Vous ne pouvez pas choisir d'équipe en tant qu'arbitre !");

                           }
                       }

                   }

                }

            }

        }


    }
}

