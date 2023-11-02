package command.impl;

import command.Command;
import core.TextEditor;

public class TreeCommand implements Command {
    String dirName;

    public TreeCommand() {

    }

    @Override
    public void execute() {
        TextEditor.listTree();
    }
}
