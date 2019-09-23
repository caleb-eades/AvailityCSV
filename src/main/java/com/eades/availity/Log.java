package com.eades.availity;

import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public enum LogLevel {
        DEBUG (4), INFO (3),
        WARN (2), ERROR (1);

        private int priority;
        LogLevel(int priority) {
            this.priority = priority;
        }

        public boolean greaterThanEqualTo(LogLevel other) {
            return this.priority >= other.priority;
        }
    }

    private static Log instance = null;
    private static LogLevel logLevel = LogLevel.WARN;
    private static String logDirectory;

    private Log() {
        this.logDirectory = System.getProperty("user.dir");
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    private void log(Object msg) {
        String timeStamp = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] ";
        System.out.println(timeStamp + msg);
    }

    public void debug(Object msg) {
        if (logLevel.greaterThanEqualTo(LogLevel.DEBUG)) {
            msg = "[DEBUG] " + msg;
            log(msg);
        }
    }

    public void info(Object msg) {
        if (logLevel.greaterThanEqualTo(LogLevel.INFO)) {
            msg = "[INFO] " + msg;
            log(msg);
        }
    }

    public void warn(Object msg) {
        if (logLevel.greaterThanEqualTo(LogLevel.WARN)) {
            msg = "[WARN] " + msg;
            log(msg);
        }
    }

    public void error(Object msg) {
        if (logLevel.greaterThanEqualTo(LogLevel.ERROR)) {
            msg = "[ERROR] " + msg;
            log(msg);
        }
    }
}