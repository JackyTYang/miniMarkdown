import core.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void run(){
        String filePath = "input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(">"+line);
                Invoker.invoke(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
