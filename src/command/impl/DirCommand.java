package command.impl;

import command.Command;

/**
 * @Author: lmy
 * @Date: 2023/11/3 10:06
 */
public class DirCommand implements Command {

    String dir;

    public DirCommand(String dirName){
        dir = dirName;
    }
    @Override
    public void execute() {

    }
}
