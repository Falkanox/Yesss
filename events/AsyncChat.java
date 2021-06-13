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
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChat implements Listener {

    RedTeam redTeamClass;
    BlueTeam blueTeamClass;
    BasicScoreboard basicScoreboard;
    JPerPlayerMethodBasedScoreboard scoreboard;

    private Bingo main;
    public AsyncChat(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.main = main;
        this.scoreboard = scoreboard;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        redTeamClass = new RedTeam(scoreboard, main);
        blueTeamClass = new BlueTeam(scoreboard, main);
        basicScoreboard = new BasicScoreboard(scoreboard, main);

        Player p = e.getPlayer();

        if(main.isState(GState.WAITING)){

            if(scoreboard.findTeam("Bleue").get().isOnTeam(p.getUniqueId())){

                e.setFormat("§bBleue " + "%1$s" + " §f: §7" + "%2$s");


            } else if(scoreboard.findTeam("Rouge").get().isOnTeam(p.getUniqueId())){

                e.setFormat("§cRouge " + "%1$s" + " §f: §7" + "%2$s");

            } else {

                e.setFormat("§7" + "%1$s" + " §f: §7" + "%2$s");

            }

        }

    }

}
