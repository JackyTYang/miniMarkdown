public class SaveCommand implements Command{
    @Override
    public void execute() {
        FileManagement.saveFile();
    }
}
