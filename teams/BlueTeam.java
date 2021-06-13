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

        if (player.isOnline()) {

            if (blueTeam.get().getEntities().size() < 4) {

                if (!blueTeam.get().isOnTeam(player.getUniqueId())) {

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
                if (cooldowns.containsKey("Blue")) {
                    if (cooldowns.get("Blue") > System.currentTimeMillis()) {
                        timeleft = (cooldowns.get("Blue") - System.currentTimeMillis()) / 100;
                    }
                }

            }
        }.runTaskTimerAsynchronously(Bingo.getPlugin(Bingo.class),20,0);


        new BukkitRunnable() {

            @Override
            public void run() {

                countDownScoreBoard(0);

                if (countdown > 0) {
                    countdown--;
                    cooldowns.put("Blue", System.currentTimeMillis() + (1000));
                }

            }
        }.runTaskTimerAsynchronously(Bingo.getPlugin(Bingo.class),0,20);
    }

    private void countDownScoreBoard(long timeleft) {

        for (UUID redTeamPlayers : blueTeam.get().getEntities()) {
            Player player = Bukkit.getPlayer(redTeamPlayers);
            if (Bukkit.getOnlinePlayers().contains(player)) {

                scoreboard.setLines(player,
                        "&c",
                        "&fJoueurs:&7 " + Bukkit.getOnlinePlayers().size(),
                        "&e",
                        "&fVotre équipe: §bBleue",
                        "&b",
                        "&fStatut: §7En attente...",
                        "&f",
                        "&fTimer: " + countdown + ":" + timeleft + "0",
                        "&a");
                scoreboard.updateScoreboard();
            }
        }
    }
}
