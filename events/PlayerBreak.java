package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreak implements Listener  {

    private Bingo main;
    public PlayerBreak(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){

        if(main.isState(GState.WAITING)){

            if(!e.getPlayer().isOp()){

                e.setCancelled(true);

            }

        }

    }
}
