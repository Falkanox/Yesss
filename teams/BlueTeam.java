package fr.falkanox.bingo.teams;

import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.Bingo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BlueTeam {

    private JScoreboardTeam team;
    private JPerPlayerMethodBasedScoreboard scoreboard;
    private Bingo main;


    public BlueTeam(JScoreboardTeam team, JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.team = team;
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addToBlueTeam(UUID uuid, Player p) {

        if (uuid != null) {

            if (team.getEntities().size() < 4) {

                if (!team.isOnTeam(uuid) || !main.jredTeam.isOnTeam(uuid)) {

                    team.addPlayer(p);
                    addToScoreboard(p);
                    countDown();
                    p.sendMessage(main.prefix + "Vous avez rejoint l'équipe §bbleue §eavec succès !");

                } else if(main.jredTeam.isOnTeam(uuid)){

                    main.jredTeam.removePlayer(p);
                    team.addPlayer(p);
                    addToScoreboard(p);
                    countDown();
                    p.sendMessage(main.prefix + "Vous avez rejoint l'équipe §bbleue §eavec succès !");

                } else p.sendMessage(main.prefix + "Vous êtes déjà dans cette équipe !");

            } else p.sendMessage(main.error + "Cette équipe est au complet !");


        }

    }

    public void addToScoreboard(Player p) {

        scoreboard.addPlayer(p);
        scoreboard.setTitle(p, "§6§lBINGO");
        scoreboard.setLines(p,
                "§c",
                "§fJoueurs:§7 " + Bukkit.getOnlinePlayers().size(),
                "§e",
                "§fVotre équipe: §bBleue",
                "§b",
                "§fStatut: §7En attente...",
                "§f",
                "§fTimer: §700:00",
                "§a");

        scoreboard.updateScoreboard();

    }

    public void countDown() {
        new BukkitRunnable() {
            @Override
            public void run() {

                    for (UUID player : team.getEntities()) {
                        countDownScoreBoard(Bukkit.getPlayer(player));
                    }
                }
        }.runTaskTimerAsynchronously(main,0,20);
    }

    private void countDownScoreBoard(Player p) {

        if(Bukkit.getOnlinePlayers().contains(p)){

            scoreboard.setLines(p,
                    "§c",
                    "§fJoueurs:§7 " + Bukkit.getOnlinePlayers().size(),
                    "§e",
                    "§fVotre équipe: §bBleue",
                    "§b",
                    "§fStatut: §7En attente...",
                    "§f",
                    "§fTimer: §700:00",
                    "§a");

        }

    }
}
