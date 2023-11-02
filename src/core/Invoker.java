package core;

import java.util.ArrayList;
import java.util.List;
import command.Command;

public class Invoker {
    public static List<String> commands = new ArrayList<>();
    public static int historyIndex = -1;

    public static List<String> fileName = new ArrayList<>();
    public static int fileID = 0;

    public static void invoke(String c){
        MyLogger.recordCommand(c);
        if(Parser.returnCategory(c).equals("load")){
            MyLogger.openNewFile(Parser.parser(c).get(1),++fileID);
        }
        if(Parser.returnCategory(c).equals("save")){
            MyLogger.closeFile();
        }//日志记录

        if(c.equals("undo")){
            undo();
        }
        else if(c.equals("redo")){
            redo();
        }
        else{
            String normalizedCommand = Parser.normalizedCommand(c, TextEditor.getInstance());
            commands.add(++historyIndex,normalizedCommand);
            Command commandImpl = Parser.getCommand(normalizedCommand);
            commandImpl.execute();
        }
    };

    public static void undo(){
        if(historyIndex!=-1){
            String commandLine = commands.get(historyIndex);
            if(Parser.returnCategory(commandLine).equals("insert") || Parser.returnCategory(commandLine).equals("delete")){
                Command command = Parser.getCommand(Parser.reverseCommand(commandLine, TextEditor.getInstance()));
                command.execute();
                historyIndex--;
            }
        }
    }

    public static void redo(){
        if(historyIndex<commands.size()-1){//说明上一条执行了undo
            historyIndex++;
            Command command = Parser.getCommand(commands.get(historyIndex));
            command.execute();
        }
    }
}