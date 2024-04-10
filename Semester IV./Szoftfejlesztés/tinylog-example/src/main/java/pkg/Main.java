package pkg;

import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        Logger.error("This is an ERROR message");
        Logger.warn("This is a WARN message");
        Logger.info("This is an INFO message");
        Logger.debug("This is a DEBUG message");
        Logger.trace("This is a TRACE message");
        Logger.info("Java version is {}", System.getProperty("java.version"));
        Logger.trace("Available free memory: {} kB", () -> Runtime.getRuntime().freeMemory() / 1024);
        Logger.error(new RuntimeException("Oops, something went wrong"), "An exception occurred");
    }

}
