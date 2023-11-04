import core.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    private static void clearTestFiles(){
        String logPath= "log.txt";
        String mdPath="test.md";
        String md2Path="test2.md";
        for(String testPath: new String[]{logPath, mdPath,md2Path}) {
            try (FileWriter writer = new FileWriter(testPath, false)) {
                System.out.println("File contents cleared successfully.");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    public static void run(){
        String inputPath = "input.txt";
        clearTestFiles();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
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
