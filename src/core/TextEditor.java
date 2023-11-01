package core;

import core.FileManagement;

import java.util.List;

/**
 * 所有操作都默认是0基址的
 */
public class TextEditor {
    /**
     * 返回传入的文本内容对应的第一个行号
     * 传入的是不带# - + * 的文本内容
     * @param name
     * @return
     */
    public int getIndex(String name){
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
    public int getLayer(int index) {
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
    public String getName(int index){
        List<String> lines = FileManagement.lines;
        String line = lines.get(index);
        String[] split = line.split(" ");

        return split[split.length-1];
    }
    public static void insert(int lineNo, String str){
        List<String> lines = FileManagement.lines;
        lines.add(lineNo,str);
    }

    public static void delete(int lineNo, String str){
        List<String> lines = FileManagement.lines;
        lines.remove(lineNo);
    }


    public static void list(){
        for (String line : FileManagement.lines) {
            System.out.println(line);
        }
    }

    public static void listTree(String dirName){
        System.out.println("listTree");
    }
}
