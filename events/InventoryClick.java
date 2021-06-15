package fr.falkanox.bingo.events;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import fr.falkanox.bingo.teams.BasicScoreboard;
import fr.falkanox.bingo.teams.BlueTeam;
import fr.falkanox.bingo.teams.RedTeam;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class InventoryClick implements Listener {

    RedTeam redTeamClass;
    BlueTeam blueTeamClass;
    BasicScoreboard basicScoreboard;
    JPerPlayerMethodBasedScoreboard scoreboard;

    private Bingo main;
    public InventoryClick(JPerPlayerMethodBasedScoreboard scoreboard,Bingo main){
        this.main = main;
        this.scoreboard = scoreboard;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        redTeamClass = new RedTeam(scoreboard, main);
        blueTeamClass = new BlueTeam(scoreboard, main);
        basicScoreboard = new BasicScoreboard(scoreboard, main);

        Player p = (Player) e.getWhoClicked();

        if(main.isState(GState.WAITING)){

            if(!p.isOp()){

                e.setCancelled(true);

            }

            if(e.getCurrentItem() == null) return;
            if(e.getCurrentItem().getItemMeta() == null) return;

            UUID uuid = p.getUniqueId();

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cÉquipe Rouge")){

                redTeamClass.addToRedTeam(p);

            }

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bÉquipe Bleue")){

                blueTeamClass.addToBlueTeam(p);

            }

        }

    }

}
