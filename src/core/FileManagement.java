package core;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

public class FileManagement {

    //内存中的文件以列表存储
    public static List<String> lines;
    //当前文件路径
    public static String filePath;

    public static void loadFile(String dir) {
        lines = new ArrayList<>();
        if (dir == null || dir.isEmpty() || !isValidPath(dir)) {
            throw new IllegalArgumentException("不合法的文件路径");
        }
        if (filePath == null || filePath.isEmpty() || !filePath.equals(dir)) filePath = dir;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

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

            filePath = null;
            lines = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

}