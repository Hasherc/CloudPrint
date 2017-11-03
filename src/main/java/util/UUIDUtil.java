package util;

import java.util.UUID;

/**
 * @author qming
 * @ 17-8-10
 */
public class UUIDUtil {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");

    }
}
