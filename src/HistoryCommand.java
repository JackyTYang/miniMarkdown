public class HistoryCommand implements Command{

    int num = -1;

    HistoryCommand(int num){
        this.num = num;
    }

    @Override
    public void execute() {
        MyLogger.history(num);
    }
}
