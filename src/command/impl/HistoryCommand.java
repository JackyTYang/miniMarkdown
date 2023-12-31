package command.impl;

import command.Command;
import core.MyLogger;

public class HistoryCommand implements Command {

    int num;

    public HistoryCommand(int num){
        this.num = num;
    }

    @Override
    public void execute() {
        MyLogger.history(num);
    }
}
