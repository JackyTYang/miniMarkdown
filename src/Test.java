import core.FileManagement;
import core.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void run(){
//        String filePath = "input.txt";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(">"+line);
//                Invoker.invoke(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading the file: " + e.getMessage());
//        }

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
