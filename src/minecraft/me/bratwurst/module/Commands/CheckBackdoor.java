package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;

public class CheckBackdoor extends Command {
    public CheckBackdoor() {
        super("checkBd", "checkBackdoor", " Schaut ob das Backdoor plugin auf dem server ist!");
    }
    @Override
    public void onCommand(String command, String[] args){

    }
}
