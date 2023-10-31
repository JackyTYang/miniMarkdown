public class TreeCommand implements Command{
    String dirName;

    public TreeCommand(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void execute() {
        TextEditor.listTree(dirName);
    }
}
