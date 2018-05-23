package com.vlad.loganalyzer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogAnalyzer {
    private String path;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;

    public LogAnalyzer(String path, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this.path = path;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public List<LogToken> parseLog() throws IOException {
        List<LogToken> logTokenList = new ArrayList<>();
        File file = new File(path);
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String log;
            while ((log = bufferedReader.readLine()) != null) {
                LogToken logToken = new LogToken(parseTime(log), parseMethod(log), parseMessage(log));
                if (logToken.getTime().isAfter(timeFrom) && logToken.getTime().isBefore(timeTo)) {
                    logTokenList.add(logToken);
                }
            }
        }
        return logTokenList;
    }


    private LocalDateTime parseTime(String logText) {
        String time = logText.substring(logText.indexOf("[") + 1, logText.indexOf("]"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        return LocalDateTime.parse(time, formatter.withLocale(Locale.US));
    }

    private HttpMethod parseMethod(String logText) {
        int position = logText.indexOf("\"") + 1;
        String method = logText.substring(position, position + 4);
        if (method.equals("POST")) {
            return HttpMethod.POST;
        } else {
            return HttpMethod.GET;
        }
    }

    private String parseMessage(String logText) {
        int position = logText.indexOf(" /") + 1;
        return logText.substring(position, logText.length());
    }
}
