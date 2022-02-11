package jsonplaceholder.utils;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class LogUtil {

    private static final Logger log = LoggerFactory.getLogger(LogUtil.class);
    private static int number;


    private static String requestType;

    public LogUtil() {
        LogUtil.setNumber(1);
    }

    public static void infoLog(String nameStep) {
        number++;
        log.info("Step {}: " + nameStep, number);

    }

    public static void setNumber(int number) {
        LogUtil.number = number;
    }

    public static void setRequestType(String requestType) {
        LogUtil.requestType = requestType;
    }
}
