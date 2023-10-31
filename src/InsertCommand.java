public class InsertCommand implements Command{

    int lineNo;
    String str;

    public InsertCommand(int lineNo, String str) {
        this.lineNo = lineNo;
        this.str = str;
    }

    @Override
    public void execute() {
        TextEditor.insert(lineNo,str);
    }
}
