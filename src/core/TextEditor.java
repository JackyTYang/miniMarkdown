package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.io.File;

/**
 * 所有操作都默认是0基址的
 */
public class TextEditor {

    /**
     * 静态内部类单例模式 实现
     * 调用方法TextEditor.getInstance()
     */
    private static class TextEditorHolder{
        private static final TextEditor INSTANCE = new TextEditor();
    }

    private TextEditor(){}

    public static TextEditor getInstance(){
        return TextEditorHolder.INSTANCE;
    }

    /**
     * 返回传入的文本内容对应的第一个行号
     * 传入的是不带# - + * 的文本内容
     * @param name
     * @return
     */
    public static int getIndex(String name){
        List<String> lines = FileManagement.lines;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(name)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 返回指定行的#数量
     * @param index
     * @return
     */
    public static int getLayer(int index) {
        List<String> lines = FileManagement.lines;

        String line = lines.get(index);
        int count = line.length() - line.replace("#", "").length();
        return count;
    }

    /**
     * 返回对应行号文本内容
     * @param index
     * @return
     */
    public static String getName(int index){
        List<String> lines = FileManagement.lines;
        String line = lines.get(index);
        String[] split = line.split(" ");

        return split[split.length-1];
    }

    /**
     * append-tail传入lineNo = -1
     */
    public static void insert(int lineNo, String str){
        List<String> lines = FileManagement.lines;
        if (lineNo == -1 || lineNo > (FileManagement.lines.size() - 1)){
            lines.add(str);
        }else {
            lines.add(lineNo,str);
        }

    }

    public static void delete(int lineNo) {
        int n = FileManagement.lines.size();
        if (lineNo == -1 || lineNo >= n) lineNo = n - 1;
        List<String> lines = FileManagement.lines;
        lines.remove(lineNo);
    }


    public static void list(){
        System.out.println();
        for (String line : FileManagement.lines) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void listTree(){
        System.out.println("listTree");
        List<String> lines = FileManagement.lines;
        List<String> nodes = new ArrayList<>();
        List<Integer> depth = new ArrayList<>();

        int pre_depth = 0;
        for (String s : lines) {
            if (s.startsWith("#")) {
                int count = 0;
                while (s.charAt(count) == '#'){
                    count++;
                }
                pre_depth = count;
                nodes.add((s.split(" "))[1]);
                depth.add(count);
            } else {
                if (s.startsWith("*")){
                    nodes.add("." + s.split(" ")[1]);
                }else
                    nodes.add(s);
                depth.add(pre_depth);
            }
        }

        for (int i = 0; i < lines.size(); ++i){
            for (int j = 1; j < depth.get(i); ++j) System.out.print("    ");
            if (i == lines.size() - 1){
                System.out.print("\u2514\u2500\u2500 ");//"└── "
            }else{
                if (Objects.equals(depth.get(i), depth.get(i + 1))){
                    System.out.print("\u251C\u2500\u2500 ");//"├── "
                }else{
                    System.out.print("\u2514\u2500\u2500 ");//"└── "
                }
            }
            System.out.println(nodes.get(i));
        }
    }

    public static void dirTree(String dir){
        String rootPath = dir; // 设置根目录路径
        File rootDirectory = new File(rootPath);

        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            System.out.println(rootDirectory.getName());
            printDirectoryTree(rootDirectory, "");
        } else {
            System.out.println("Root directory does not exist.");
        }
    }

    //被dirTree调用的工具函数（递归
    public static void printDirectoryTree(File directory, String prefix) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String treePrefix = (i == files.length - 1) ? "\u2514\u2500\u2500 " : "\u251C\u2500\u2500 ";

                System.out.println(prefix + treePrefix + file.getName());

                if (file.isDirectory()) {
                    String newPrefix = prefix + (i == files.length - 1 ? "    " : "\u2502   ");
                    printDirectoryTree(file, newPrefix);
                }
            }
        }
    }




}
