package fr.falkanox.bingo.events;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import fr.falkanox.bingo.teams.BasicScoreboard;
import fr.falkanox.bingo.teams.BlueTeam;
import fr.falkanox.bingo.teams.RedTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    RedTeam redTeamClass;
    BlueTeam blueTeamClass;
    BasicScoreboard basicScoreboard;
    JPerPlayerMethodBasedScoreboard scoreboard;

    private Bingo main;
    public PlayerQuit(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.main = main;
        this.scoreboard = scoreboard;
    }

    @EventHandler
    public void onQuit(PlayerPr e){

        redTeamClass = new RedTeam(scoreboard, main);
        blueTeamClass = new BlueTeam(scoreboard, main);
        basicScoreboard = new BasicScoreboard(scoreboard, main);

        e.setQuitMessage(null);

        Player p = e.getPlayer();

        if(main.isState(GState.WAITING)){

            for(Player pls : Bukkit.getOnlinePlayers()){

                pls.sendMessage(main.prefix + "§7" + p.getName() + " §ea quitté la partie ! §a(" + (Bukkit.getOnlinePlayers().size() -1) + "/10)");

            }

            if(scoreboard.findTeam("Bleue").get().isOnTeam(p.getUniqueId())){
                scoreboard.findTeam("Bleue").get().removePlayer(p);
            }

            if(scoreboard.findTeam("Rouge").get().isOnTeam(p.getUniqueId())){
                scoreboard.findTeam("Rouge").get().removePlayer(p);
            }



        }

    }

}
