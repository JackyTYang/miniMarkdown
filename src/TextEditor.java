import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;

public class TextEditor {
    public int getIndex(String name){
        ArrayList<String> lines = FileManagement.getLines();
        return lines.indexOf(name);
    }
    public int getLayer(int index) {
        return 0;
    }
    public String getName(int index){
        return "";
    }
    public static void insert(int lineNo, String str){

    }

    public static void delete(int lineNo, String str){

    }


    public static void list(){
        System.out.println("list");
    }

    public static void listTree(String dirName){
        System.out.println("listTree");
    }
}
