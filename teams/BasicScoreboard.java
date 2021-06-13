package fr.falkanox.bingo.teams;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.Bingo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

public class BasicScoreboard {

    private JPerPlayerMethodBasedScoreboard scoreboard;
    private Bingo main;
    private Optional<JScoreboardTeam> blueTeam;
    private Optional<JScoreboardTeam> redTeam;

    public BasicScoreboard(JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addBasicScoreboard(Player p) {

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (blueTeam.get().getEntities().isEmpty() && redTeam.get().getEntities().isEmpty()) {

                scoreboard.addPlayer(player);
                scoreboard.setTitle(p, "&6&lBINGO");
                scoreboard.setLines(p, "&c", "&7Choisissez une équipe...", "&e");

            } else if (blueTeam.get().getEntities().isEmpty() && !redTeam.get().getEntities().isEmpty()) {

                if (!redTeam.get().isOnTeam(player.getUniqueId())) {

                    scoreboard.addPlayer(player);
                    scoreboard.setTitle(p, "&6&lBINGO");
                    scoreboard.setLines(p, "&c", "&7Choisissez une équipe...", "&e");
                }

            } else if (!blueTeam.get().getEntities().isEmpty() && redTeam.get().getEntities().isEmpty()) {

                if (!blueTeam.get().isOnTeam(player.getUniqueId())) {

                    scoreboard.addPlayer(player);
                    scoreboard.setTitle(p, "&6&lBINGO");
                    scoreboard.setLines(p, "&c", "&7Choisissez une équipe...", "&e");
                }

            }
        }

    }
}
