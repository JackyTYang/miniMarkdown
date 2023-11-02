package command.impl;

import command.Command;
import core.FileManagement;

public class SaveCommand implements Command {
    @Override
    public void execute() {
        FileManagement.saveFile();
    }
}
