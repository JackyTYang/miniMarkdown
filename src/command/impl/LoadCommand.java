package command.impl;

import command.Command;
import core.FileManagement;

public class LoadCommand implements Command {

    String directory;

    public LoadCommand(String directory) {
        this.directory = directory;
    }

    @Override
    public void execute() {
        FileManagement.loadFile(directory);
    }
}
