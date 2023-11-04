package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyLogger {
    private static String currentFile;
    public static String filePath= "log.txt";
    private static List<String> logs = new ArrayList<>();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime sessionStartTime = LocalDateTime.now();

    private static final HashMap<String , List<LocalDateTime>>  file_times = new HashMap<>();

    static {
        try {
            logs = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        recordCommand("session start :"+sessionStartTime.format(TIMESTAMP_FORMAT));
    }

    public static void recordCommand(String str){//invoker调用，记录每一条command到Mylogger的数据结构中
        String timestampedLog = LocalDateTime.now().format(TIMESTAMP_FORMAT) + " - " + str;
        logs.add(timestampedLog);
        // Asynchronously write timestampedLog to the log file
        executor.submit(() -> {
            try {
                Files.write(Paths.get(filePath), (timestampedLog + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error writing to log file: " + e.getMessage());
            }
        });
    }
    public static void history(int num){
        System.out.println();
        int n = logs.size();
        int i=0;//default start from 0
        if(num>=0&&num<n)i=n-num;
        for (; i < n; i++) {
            System.out.println(logs.get(i));
        }
        System.out.println();
    }
    public static void stats(String parameter){
        System.out.println("\nsession start :" + sessionStartTime.format(TIMESTAMP_FORMAT));
        if(parameter.equals("all")) {
            for (String s : file_times.keySet()) {
                Duration duration = Duration.between(file_times.get(s).get(0), file_times.get(s).get(1));
                System.out.println(s + ": " + duration.toHours() + "h" + duration.toMinutesPart() + "m"+duration.toSecondsPart()+"s"+duration.toMillisPart()+"ms");
            }
        }
        else if(parameter.equals("current")){
            Duration duration=Duration.between(file_times.get(currentFile).get(0),LocalDateTime.now());
            System.out.println(currentFile + ": " + duration.toHours() + "h" + duration.toMinutesPart() + "m"+duration.toSecondsPart()+"s"+duration.toMillisPart()+"ms");
        }
        System.out.println();
    }
    public static String openNewFile(String fileName){
        currentFile=fileName;
        file_times.put(fileName, new ArrayList<>());
        file_times.get(fileName).add(LocalDateTime.now());
        return fileName;
    }
    public static void closeFile(String fileName){
        file_times.get(fileName).add(LocalDateTime.now());
    }
}
