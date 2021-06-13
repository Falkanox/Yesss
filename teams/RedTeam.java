package fr.falkanox.bingo.teams;

import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.Bingo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RedTeam {

    private JPerPlayerMethodBasedScoreboard scoreboard;
    private Bingo main;
    private Optional<JScoreboardTeam> redTeam;
    private int countdown = 20;
    Map<String, Long> cooldowns = new HashMap<String, Long>();
    private long timeleft;

    public RedTeam(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addToRedTeam(Player player) {

        redTeam = scoreboard.findTeam("Rouge");

        if (player.isOnline()) {

            if (redTeam.get().getEntities().size() < 4) {

                if (!redTeam.get().isOnTeam(player.getUniqueId())) {

                    redTeam.get().addPlayer(player);
                    addToScoreboard(player);
                    countDown();
                    player.sendMessage(main.prefix + "Vous avez rejoint l'équipe §crouge §eavec succès !");

                } else player.sendMessage(main.prefix + "Vous êtes déjà dans cette équipe !");

            } else player.sendMessage(main.error + "Cette équipe est au complet !");


        }

    }

    public void addToScoreboard(Player p) {

        scoreboard.addPlayer(p);
        scoreboard.setTitle(p, "&6&lBINGO");
        scoreboard.updateScoreboard();

    }

    private void countDown() {

        new BukkitRunnable() {

            @Override
            public void run() {

                countDownScoreBoard(timeleft);

            }

        }.runTaskTimerAsynchronously(Bingo.getPlugin(Bingo.class),10,0);

        new BukkitRunnable() {

            @Override
            public void run() {
                //The milliseconds can cause an error
                //java.lang.IllegalStateException: Player is either on another team or not on any team. Cannot remove from team 'line1'.
                if (cooldowns.containsKey("red")) {

                    if (cooldowns.get("red") > System.currentTimeMillis()) {

                        timeleft = (cooldowns.get("red") - System.currentTimeMillis()) / 100;

                    }

                }

            }

        }.runTaskTimerAsynchronously(Bingo.getPlugin(Bingo.class),20,0);


        new BukkitRunnable() {

            @Override
            public void run() {

                countDownScoreBoard( 0);


                if (countdown > 0) {
                    countdown--;
                    cooldowns.put("red", System.currentTimeMillis() + (1000));
                }

            }
        }.runTaskTimerAsynchronously(Bingo.getPlugin(Bingo.class),0,20);
    }

    private void countDownScoreBoard(long timeleft) {

        for (UUID redTeamPlayers : redTeam.get().getEntities()) {
            Player player = Bukkit.getPlayer(redTeamPlayers);
            if (Bukkit.getOnlinePlayers().contains(player)) {

                scoreboard.setLines(player,
                        "&c",
                        "&fJoueurs:&7 " + Bukkit.getOnlinePlayers().size(),
                        "&e",
                        "&fVotre équipe: &cRouge",
                        "&b",
                        "&fStatut: &7En attente...",
                        "&f",
                        "&fTimer: "+countdown+":"+timeleft+"0",
                        "&a");
                scoreboard.updateScoreboard();
            }
        }
    }

}
