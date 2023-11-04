package core;

import command.*;
import command.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> parse(String command){
        ArrayList<String> args=new ArrayList<>(Arrays.asList(command.split(" ")));
        switch (args.get(0)) {
            case "append-head" -> {
                args.set(0,"insert");
                args.add(1,"0");
            }
            case "append-tail"-> {
                args.set(0,"insert");
                args.add(1,"-1");
            }
            case "insert"->{
                if(args.size()==3)args.add(1,"-1");
                else args.set(1,String.valueOf(Integer.parseInt(args.get(1))-1));
            }
            case "delete" -> {
                int lineNumber;
                try {
                    lineNumber = Integer.parseInt(args.get(1))-1;//1-index to 0-index
                } catch (NumberFormatException e) {
                    lineNumber = TextEditor.getIndex(args.get(1));//give name get index
                }
                args.set(0,"delete");
                args.set(1,String.valueOf(lineNumber));
                args.add("#".repeat(TextEditor.getLayer(lineNumber)));//repeat "#"
                args.add(TextEditor.getName(lineNumber));//give index get name
            }
            case "history"->{
                if(args.size()==1)args.add("-1");
            }
            default -> {}
        }

        return args;
    }
    public static List<String> reverseCommand(List<String> args){//insert<->delete
        if(args.get(0).equals("insert"))args.set(0,"delete");
        else if(args.get(0).equals("delete"))args.set(0,"insert");
        return args;
    }
    public static Command getCommand(List<String> args){
        return switch (args.get(0)) {
            case "load" -> new LoadCommand(args.get(1));
            case "save" -> new SaveCommand();
            case "insert" -> new InsertCommand(Integer.parseInt(args.get(1)), args.get(2)+" "+args.get(3));
            case "delete" -> new DeleteCommand(Integer.parseInt(args.get(1)), args.get(2)+" "+args.get(3));
            case "list" -> new ListCommand();
            case "listTree" -> new TreeCommand();
            case "history" -> new HistoryCommand(Integer.parseInt(args.get(1)));
            case "stats" -> new StatsCommand(args.get(1));
            case "dirTree"-> new DirCommand(args.get(1));
            default -> null;
        };
    }
}
