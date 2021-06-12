package fr.falkanox.bingo.events;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private Bingo main;
    public PlayerQuit(Bingo main){
        this.main = main;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        e.setQuitMessage(null);

        Player p = e.getPlayer();

        if(main.isState(GState.WAITING)){

            for(Player pls : Bukkit.getOnlinePlayers()){

                pls.sendMessage(main.prefix + "§7" + p.getName() + " §ea quitté la partie ! §a(" + (Bukkit.getOnlinePlayers().size() -1) + "/10)");

            }

           /*if(main.teams.inRedTeam(p.getUniqueId())){

                main.teams.getRedTeam().remove(p.getUniqueId());
                main.teams.team.removePlayer(p);

            } else if(main.teams.inBlueTeam(p.getUniqueId())){

                main.teams.getBlueTeam().remove(p.getUniqueId());
                main.teams.team.removePlayer(p);

            }*/

            if(main.jredTeam.isOnTeam(p.getUniqueId())){

                main.jredTeam.removePlayer(p);

            }

            if(main.jblueTeam.isOnTeam(p.getUniqueId())){

                main.jblueTeam.removePlayer(p);

            }

        }

    }

}
