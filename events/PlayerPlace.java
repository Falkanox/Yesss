package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPlace implements Listener {

    private Bingo main;
    public PlayerPlace(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){

        if(main.isState(GState.WAITING)){

            if(!e.getPlayer().isOp()){

                e.setCancelled(true);

            }

        }

    }

}
