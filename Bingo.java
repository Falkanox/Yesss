package fr.falkanox.bingo;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.inventorys.TeamInventory;
import fr.falkanox.bingo.registers.RegisterCommands;
import fr.falkanox.bingo.registers.RegisterEvents;
import fr.falkanox.bingo.scoreboard.WaitingScoreboard;
import fr.falkanox.bingo.states.GState;
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

    public JPerPlayerMethodBasedScoreboard scoreboard;
    public JScoreboardTeam jblueTeam;
    public JScoreboardTeam jredTeam;
    public BlueTeam blueTeam;
    public RedTeam redTeam;

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

        scoreboard = new JPerPlayerMethodBasedScoreboard();

        jblueTeam = scoreboard.createTeam("Bleue", "§bBleue ", ChatColor.AQUA);
        blueTeam = new BlueTeam(jblueTeam, scoreboard, this);


        jredTeam = scoreboard.createTeam("Rouge", "§cRouge ", ChatColor.RED);
        redTeam = new RedTeam(jredTeam, scoreboard, this);

    }

    public void onDisable(){

    }

    public void setState(GState state) {
        this.state = state;
    }

    public boolean isState(GState state){
        return this.state == state;
    }

    public void addBasicScoreboard(Player p){

        for(Player pls : Bukkit.getOnlinePlayers()){

            if(!jblueTeam.isOnTeam(pls.getUniqueId()) && !jredTeam.isOnTeam(pls.getUniqueId())){

                JPerPlayerMethodBasedScoreboard sc = new JPerPlayerMethodBasedScoreboard();

                sc.addPlayer(pls);
                sc.setTitle(p, "§6§lBINGO");
                sc.setLines(p,"§c","§7Choisissez une équipe...","§e");

            }

        }

    }

}
