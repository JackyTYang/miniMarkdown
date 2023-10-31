public class DeleteCommand implements Command{
    int lineNo;

    String str;

    Boolean ifLineNo;

    public DeleteCommand(int lineNo, String str, Boolean ifLineNo) {
        this.lineNo = lineNo;
        this.str = str;
        this.ifLineNo = ifLineNo;
    }

    @Override
    public void execute() {
        TextEditor.delete(lineNo,str,ifLineNo);
    }
}
