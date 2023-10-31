import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static Command getCommand(String command){//将command转化为一个CommandImpl对象返回给invoke
        return new LoadCommand("C://....");//举例子
    }

    public static List<String> parser(String command){
        return new ArrayList<>();//每一种方法，将三个存入list中。第一个参数为方法，第二个为。。以此类推
    }

    public static String normalizedCommand(String command){
        return command;//append和insert等都变成insert lineNo content的形式，delete lineNo和delete content都变成delete lineNo content的形式,需要调用textEditor获取相关文本
    }

    public static String reverseCommand(String command){
        return "";//insert lineNo abc -> delete lineNo,delete lineNo content -> insert lineNo content
    }

    public static String returnCategory(String command){
        return "insert";
    }
}
