package fr.falkanox.bingo.teams;

import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.Bingo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BlueTeam {

    private Bingo main;
    private Optional<JScoreboardTeam> blueTeam;
    private Optional<JScoreboardTeam> redTeam;
    private JPerPlayerMethodBasedScoreboard scoreboard;
    private int countdown = 20;
    Map<String, Long> cooldowns = new HashMap<String, Long>();
    private long timeleft;

    public BlueTeam(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addToBlueTeam(Player player) {

        blueTeam = scoreboard.findTeam("Bleue");
        redTeam = scoreboard.findTeam("Rouge");

        if (player.isOnline()) {

            if (blueTeam.get().getEntities().size() < 4) {

                if (!blueTeam.get().isOnTeam(player.getUniqueId()) && !redTeam.get().isOnTeam(player.getUniqueId())) {

                    blueTeam.get().addPlayer(player);
                    addToScoreboard(player);
                    countDown();

                    player.sendMessage(main.prefix + "Vous avez rejoint l'équipe §bbleue §eavec succès !");

                } else if(redTeam.get().isOnTeam(player.getUniqueId())){

                    redTeam.get().removePlayer(player);
                    blueTeam.get().addPlayer(player);
                    addToScoreboard(player);
                    countDown();

                    player.sendMessage(main.prefix + "Vous avez rejoint l'équipe §bbleue §eavec succès !");

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

                for (UUID player : blueTeam.get().getEntities()) {
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
                    "&fVotre équipe: &bBleue",
                    "&b",
                    "&fStatut: &7En attente...",
                    "&f",
                    "&fTimer: &700:00",
                    "&a");

            scoreboard.updateScoreboard();

        }
    }
}
