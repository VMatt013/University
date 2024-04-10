package pkg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluentMain {

    private static Logger logger = LoggerFactory.getLogger(FluentMain.class);

    public static void main(String[] args) {
        logger.atError().log("This is an ERROR message");
        logger.atWarn().log("This is a WARNING message");
        logger.atInfo().log("This is an INFO message");
        logger.atDebug().log("This is a DEBUG message");
        logger.atTrace().log("This is a TRACE message");
        logger.atInfo().log("Java version is {}", System.getProperty("java.version"));
        logger.atTrace()
            .setMessage("Available free memory: {} kB")
            .addArgument(() -> Runtime.getRuntime().freeMemory() / 1024)
            .log();
        logger.atError()
            .setCause(new RuntimeException("Oops, something went wrong"))
            .log("An exception occurred");
    }

}
