package com.vlad.loganalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogAnalyzer {
    private String path;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;

    public LogAnalyzer() {
    }

    public LogAnalyzer(String path) {
        this.path = path;
    }

    public LogAnalyzer(String path, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this.path = path;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    private LocalDateTime parseTime(String logText) {
        String time = logText.substring(logText.indexOf("[") + 1, logText.indexOf("]"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        return LocalDateTime.parse(time, formatter.withLocale(Locale.US));
    }

    private String parseMethod(String logText) {
        String method = logText.substring(logText.indexOf("GET"));
        return method;
    }

    private void parse(String logText){



    }


    public static void main(String[] args) {
        String path = "64.242.88.10 - - [07/Mar/2004:16:05:49 -0800] \"GET /twiki/bin/edit/Main/Double_bounce_sender?" +
                "topicparent=Main.ConfigurationVariables HTTP/1.1\" 401 12846";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        System.out.println(logAnalyzer.parseTime(path));
        System.out.println(logAnalyzer.parseMethod(path));
    }
}
