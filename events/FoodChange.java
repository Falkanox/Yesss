package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChange implements Listener {

    private Bingo main;
    public FoodChange(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){

        if(main.isState(GState.WAITING)){

            e.setCancelled(true);

        }

    }

}
