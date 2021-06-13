package fr.falkanox.bingo.events;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.states.GState;
import fr.falkanox.bingo.teams.BasicScoreboard;
import fr.falkanox.bingo.teams.BlueTeam;
import fr.falkanox.bingo.teams.RedTeam;
import fr.falkanox.bingo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoin implements Listener {

    RedTeam redTeamClass;
    BlueTeam blueTeamClass;
    BasicScoreboard basicScoreboard;
    JPerPlayerMethodBasedScoreboard scoreboard;

    private Bingo main;
    public PlayerJoin(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.scoreboard = scoreboard;
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        redTeamClass = new RedTeam(scoreboard, main);
        blueTeamClass = new BlueTeam(scoreboard, main);
        basicScoreboard = new BasicScoreboard(scoreboard, main);

        Player p = e.getPlayer();
        Inventory inv = p.getInventory();

        if(main.isState(GState.WAITING)){

                ItemBuilder teams = new ItemBuilder(Material.CLOCK).setDisplayName("§aChoisissez votre équipe !");

                p.setInvulnerable(true);
                p.getInventory().clear();
                p.setHealth(20);
                p.setFoodLevel(20);
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setItem(4, teams.build());
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 10);

                for(Player pls : Bukkit.getOnlinePlayers()){

                    e.setJoinMessage(main.prefix + "§7" + p.getName() + " §ea rejoint la partie ! §a(" + Bukkit.getOnlinePlayers().size() + "/10)");

                }

            for (Player player : Bukkit.getOnlinePlayers()) {

                basicScoreboard.addBasicScoreboard(player);

            }

        }

    }

}
