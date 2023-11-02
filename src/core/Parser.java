package core;

import command.*;
import command.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static String combineArgs(String[] args){
        StringBuilder command= new StringBuilder();
        for(String arg:args)
            command.append(arg).append(" ");
        return command.toString();
    }
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

        List<String> elements2 = new ArrayList<>();
        for (int i = 0; i < elements.length; ++i){
            if (elements[i].charAt(0) == '#' || elements[i].charAt(0) == '*' && i < elements.length - 1){
                elements2.add(elements[i] + " " + elements[i + 1]);
                break;
            }else
                elements2.add(elements[i]);
        }

        return elements2;//每一种方法，将三个存入list中。第一个参数为方法，第二个为。。以此类推
    }

    public static String normalizedCommand(String command, TextEditor textEditor){
        List<String> args=parser(command);


//        System.out.println("args="+args);
        switch (args.get(0)) {
            case "append-head" -> {
                args.set(0,"insert");
                args.add(1,"0");
            }
            case "append-tail"-> {
                args.set(0,"insert");
                args.add(1,"-1");
            }
            case "delete" -> {
                int lineNumber;
                try {
                    lineNumber = Integer.parseInt(args.get(1));
                } catch (NumberFormatException e) {
                    lineNumber = textEditor.getIndex(args.get(1));//give name get index
                }
                args.set(0,"delete");
                args.set(1,String.valueOf(lineNumber));
                args.add("#".repeat(textEditor.getLayer(lineNumber)));//repeat "#"
                args.add(textEditor.getName(lineNumber));//give index get name
            }
            default -> {}
        }

        return combineArgs(args.toArray(new String[0]));//append和insert等都变成insert lineNo content的形式，delete lineNo和delete content都变成delete lineNo content的形式,需要调用textEditor获取相关文本
    }

    public static String reverseCommand(String command, TextEditor textEditor){
        List<String> args=parser(normalizedCommand(command,textEditor));
        if(args.get(0).equals("insert"))args.set(0,"delete");
        else if(args.get(0).equals("delete"))args.set(0,"insert");
        return combineArgs(args.toArray(new String[0]));//insert lineNo abc -> delete lineNo,delete lineNo content -> insert lineNo content
    }

    public static String returnCategory(String command){
        return parser(command).get(0);
    }
}
