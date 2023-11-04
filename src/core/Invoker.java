package core;

import java.util.ArrayList;
import java.util.List;
import command.Command;

public class Invoker {
    public static List<List<String>> commands = new ArrayList<>();
    public static int historyIndex = -1;
    public static String fileName;

    public static void invoke(String c){
        MyLogger.recordCommand(c);
        List<String> args=Parser.parse(c);
        switch (args.get(0)){
            case "load"->fileName=MyLogger.openNewFile(args.get(1));
            case "save"->MyLogger.closeFile(fileName);
            case "undo"->{undo();return;}
            case "redo"->{redo();return;}
            case "insert","delete"-> commands.add(++historyIndex,args);
            case "exit"->System.exit(0);
        }
        Parser.getCommand(args).execute();
    }

    public static void undo(){
        if(historyIndex!=-1) {
            Command command = Parser.getCommand(Parser.reverseCommand(commands.get(historyIndex)));
            command.execute();
            historyIndex--;
        }
    }

    public static void redo(){
        if(historyIndex<commands.size()-1){
            historyIndex++;
            Command command = Parser.getCommand(Parser.reverseCommand(commands.get(historyIndex)));
            command.execute();
        }
    }
}