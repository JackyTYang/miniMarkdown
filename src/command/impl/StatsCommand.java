package command.impl;

import command.Command;
import core.MyLogger;

public class StatsCommand implements Command {
    String parameter;

    public StatsCommand(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        MyLogger.stats(parameter);
    }
}
