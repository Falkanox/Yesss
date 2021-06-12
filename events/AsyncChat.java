package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChat implements Listener {

    private Bingo main;
    public AsyncChat(Bingo main){ this.main = main;}

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        Player p = e.getPlayer();

        if(main.isState(GState.WAITING)){

            if(main.jblueTeam.isOnTeam(p.getUniqueId())){

                e.setFormat("§bBleue " + "%1$s" + " §f: §7" + "%2$s");


            } else if(main.jredTeam.isOnTeam(p.getUniqueId())){

                e.setFormat("§cRouge " + "%1$s" + " §f: §7" + "%2$s");

            } else {

                e.setFormat("§7" + "%1$s" + " §f: §7" + "%2$s");

            }

        }

    }

}
