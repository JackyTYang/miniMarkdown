import java.io.InputStreamReader;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        while(true){
            command = scanner.nextLine();
            if(command.equals("exit"))
                break;
            Invoker.invoke(command);
        }
    }
}