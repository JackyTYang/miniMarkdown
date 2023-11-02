package core;

public class MyLogger {

    public static int fileID;

    public static void recordCommand(String str){//invoker调用，记录每一条command到Mylogger的数据结构中

    }

    public static void history(int num){//num为-1，则展示左右
        System.out.println("the history");
    }

    public static void status(String parameter){//参数为ALL还是current
        System.out.println("the status");
    }

    public static void openNewFile(String fileName, int fileID){
        //开启计时器。。。。。
    }

    public static void closeFile(){
        //关闭计时器
    }


}
