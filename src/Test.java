import core.FileManagement;
import core.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void run(){
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
