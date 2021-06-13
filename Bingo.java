package fr.falkanox.bingo;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.events.PlayerJoin;
import fr.falkanox.bingo.inventorys.TeamInventory;
import fr.falkanox.bingo.registers.RegisterCommands;
import fr.falkanox.bingo.registers.RegisterEvents;
import fr.falkanox.bingo.scoreboard.WaitingScoreboard;
import fr.falkanox.bingo.states.GState;
import fr.falkanox.bingo.teams.BasicScoreboard;
import fr.falkanox.bingo.teams.BlueTeam;
import fr.falkanox.bingo.teams.RedTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Bingo extends JavaPlugin {

    private RegisterEvents rv = new RegisterEvents(this);
    private RegisterCommands rm = new RegisterCommands(this);

    private GState state;

    public JScoreboardTeam blueTeam;
    public JScoreboardTeam redTeam;
    private BasicScoreboard basicScoreboard;
    private RedTeam redTeamClass;
    private BlueTeam blueTeamClass;
    private final JPerPlayerMethodBasedScoreboard scoreboard = new JPerPlayerMethodBasedScoreboard();

    public String prefix = "§7[§eBingo§7] §e";
    public String error = "§7[§eBingo§7] §c";

    public TeamInventory teamInventory = new TeamInventory();

    public WaitingScoreboard waitingScoreboard = new WaitingScoreboard(this);

    public void onEnable(){

        getServer().getWorld("world").setTime(1000);
        getServer().getWorld("world").setStorm(false);

        rv.registerEvents();
        rm.registerCommands();

        setState(GState.WAITING);

        getServer().getLogger().info(ChatColor.GREEN + "[Bingo] Plugin actif !");

        basicScoreboard = new BasicScoreboard(scoreboard, this);
        redTeamClass = new RedTeam(scoreboard, this);
        blueTeamClass = new BlueTeam(scoreboard, this);

        blueTeam = scoreboard.createTeam("Bleue", "§bBleue ", ChatColor.AQUA);
        redTeam = scoreboard.createTeam("Rouge", "§cRouge ", ChatColor.RED);
        System.out.println(scoreboard.getTeams());

        for (Player player : Bukkit.getOnlinePlayers()) {

            basicScoreboard.addBasicScoreboard(player);

        }

    }

    public void onDisable(){

        getServer().getLogger().info(ChatColor.GREEN + "[Bingo] Plugin inactif !");

    }

    public void setState(GState state) {
        this.state = state;
    }

    public boolean isState(GState state){
        return this.state == state;
    }

    public JPerPlayerMethodBasedScoreboard getScoreboard() {
        return scoreboard;
    }

}
