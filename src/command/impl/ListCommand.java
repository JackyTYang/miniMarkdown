package command.impl;

import command.Command;
import core.TextEditor;

public class ListCommand implements Command {
    @Override
    public void execute() {
        TextEditor.list();
    }
}
