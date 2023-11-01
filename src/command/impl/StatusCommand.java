package command.impl;

import command.Command;
import core.MyLogger;

public class StatusCommand implements Command {
    String parameter;

    public StatusCommand(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        MyLogger.status(parameter);
    }
}
