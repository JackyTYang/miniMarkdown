import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command getCommand(String command){//将command转化为一个CommandImpl对象返回给invoke
        List<String> elements = parser(command);
        if(elements.get(0).equals("load")){
            return new LoadCommand(elements.get(1));
        }
        else if(elements.get(0).equals("save")){
            return new SaveCommand();
        }
        else if(elements.get(0).equals("insert")){
            return new InsertCommand(Integer.parseInt(elements.get(1)),elements.get(2));
        }
        else if(elements.get(0).equals("delete")){
            return new DeleteCommand(Integer.parseInt(elements.get(1)),elements.get(2));
        }
        else if(elements.get(0).equals("list")){
            return new ListCommand();
        }
        else if(elements.get(0).equals("listTree")){
            return new TreeCommand(elements.get(1));
        }
        else if(elements.get(0).equals("history")){
            return new HistoryCommand(Integer.parseInt(elements.get(1)));
        }
        else if(elements.get(0).equals("status")){
            return new StatusCommand(elements.get(1));
        }
        else return null;
    }

    public static List<String> parser(String command){
        String[] elements = command.split(" ");
        return Arrays.asList(elements);//每一种方法，将三个存入list中。第一个参数为方法，第二个为。。以此类推
    }

    public static String normalizedCommand(String command){
        return command;//append和insert等都变成insert lineNo content的形式，delete lineNo和delete content都变成delete lineNo content的形式,需要调用textEditor获取相关文本
    }

    public static String reverseCommand(String command){
        return "";//insert lineNo abc -> delete lineNo,delete lineNo content -> insert lineNo content
    }

    public static String returnCategory(String command){
        return parser(command).get(0);
    }
}
