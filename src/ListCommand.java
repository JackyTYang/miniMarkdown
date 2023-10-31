public class ListCommand implements Command{
    @Override
    public void execute() {
        TextEditor.list();
    }
}
