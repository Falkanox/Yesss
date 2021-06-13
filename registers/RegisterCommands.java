package fr.falkanox.bingo.registers;

import fr.falkanox.bingo.Bingo;
import fr.falkanox.bingo.commands.TeamCommand;

public class RegisterCommands {

    private Bingo main;
    public RegisterCommands(Bingo main){
        this.main = main;
    }

    public void registerCommands(){

        main.getCommand("team").setExecutor(new TeamCommand(main));

    }

}
