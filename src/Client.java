import core.Invoker;

import java.util.Scanner;

public class Client {
    public static void run() {
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