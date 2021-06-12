package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDrop implements Listener {

    private Bingo main;
    public PlayerDrop(Bingo main){
        this.main = main;
    }

    @EventHandler

    public void onDrop(PlayerDropItemEvent e){

        if(main.isState(GState.WAITING)){

            if(!e.getPlayer().isOp()){

                e.setCancelled(true);

            }

        }

    }

}
