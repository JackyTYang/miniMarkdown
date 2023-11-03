package command.impl;

import command.Command;
import core.TextEditor;

public class DeleteCommand implements Command {
    int lineNo;

    public DeleteCommand(int lineNo, String str) {
        this.lineNo = lineNo;
    }

    @Override
    public void execute() {
        TextEditor.delete(lineNo);
    }
}
