package fr.falkanox.bingo.commands;

import fr.falkanox.bingo.Bingo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor  {

    private Bingo main;
    public TeamCommand(Bingo main){this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        p.sendMessage("");

        return false;
    }
}
