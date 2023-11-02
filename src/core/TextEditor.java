package core;

import core.FileManagement;

import java.util.List;

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
     * @param lineNo
     * @param str
     */
    public static void insert(int lineNo, String str){
        List<String> lines = FileManagement.lines;
        if (lineNo == -1 || lineNo > (FileManagement.lines.size() - 1)){
            lines.add(str);
        }else {
            lines.add(lineNo,str);
        }

    }

    public static void delete(int lineNo, String str){
        if ( lineNo >=0 && lineNo < FileManagement.lines.size() ){
            List<String> lines = FileManagement.lines;
            lines.remove(lineNo);
        } else {
            System.out.println("不合法的行号");
            throw new IllegalArgumentException("不合法的行号");
        }

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
