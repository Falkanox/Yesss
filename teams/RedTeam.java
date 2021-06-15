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
    private Optional<JScoreboardTeam> blueTeam;
    private int countdown = 20;
    Map<String, Long> cooldowns = new HashMap<String, Long>();
    private long timeleft;

    public RedTeam(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addToRedTeam(Player player) {

        redTeam = scoreboard.findTeam("Rouge");
        blueTeam = scoreboard.findTeam("Bleue");

        if (player.isOnline()) {

            if (redTeam.get().getEntities().size() < 4) {

                if (!blueTeam.get().isOnTeam(player.getUniqueId()) && !redTeam.get().isOnTeam(player.getUniqueId())) {

                    redTeam.get().addPlayer(player);
                    addToScoreboard(player);
                    countDown();

                    player.sendMessage(main.prefix + "Vous avez rejoint l'équipe §crouge §eavec succès !");

                } else if(blueTeam.get().isOnTeam(player.getUniqueId())){

                    blueTeam.get().removePlayer(player);
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

    public void countDown() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (UUID player : redTeam.get().getEntities()) {
                    countDownScoreBoard(Bukkit.getPlayer(player));

                }

            }

        }.runTaskTimerAsynchronously(main,0,20);

    }

    private void countDownScoreBoard(Player p) {

            if (Bukkit.getOnlinePlayers().contains(p)) {

                scoreboard.setLines(p,
                        "&c",
                        "&fJoueurs:&7 " + Bukkit.getOnlinePlayers().size(),
                        "&e",
                        "&fVotre équipe: &cRouge",
                        "&b",
                        "&fStatut: &7En attente...",
                        "&f",
                        "&fTimer: &700:00",
                        "&a");

                scoreboard.updateScoreboard();

            }
    }
}


