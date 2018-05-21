package com.vlad.loganalyzer;

import java.time.LocalDateTime;

public class LogToken {
    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    public LogToken(LocalDateTime time, HttpMethod method, String message) {
        this.time = time;
        this.method = method;
        this.message = message;
    }
}
