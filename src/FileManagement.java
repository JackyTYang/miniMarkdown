import java.io.*;
import java.util.*;

public class FileManagement {

    private static ArrayList<String> lines = new ArrayList<>();
    private static String filePath;

    public static void loadFile(String dir) {
        if (filePath.isEmpty() || !filePath.equals(dir)) filePath = dir;
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(){
        try {
            File file = new File(filePath);  // 使用之前定义的文件路径
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getLines() {
        return new ArrayList<>(lines);
    }

    public static String getFilePath() {
        return filePath;
    }

}
