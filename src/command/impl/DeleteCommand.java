package command.impl;

import command.Command;
import core.TextEditor;

public class DeleteCommand implements Command {
    int lineNo;

    String str;

    Boolean ifLineNo;

    public DeleteCommand(int lineNo, String str) {
        this.lineNo = lineNo;
        this.str = str;
    }

    @Override
    public void execute() {
        TextEditor.delete(lineNo,str);
    }
}
