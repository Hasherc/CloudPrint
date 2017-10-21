package util;

import javax.servlet.http.Cookie;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cuiqm
 * Date: 2017-10-02
 * Time: 下午3:35
 */
public class CookieUtil {
    public static boolean checkLogin(Cookie[] cookies) {
        String uuid = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName() == "JSessionUUID") {
                uuid = cookie.getValue();
            }
        }
        System.out.println(uuid);
        return uuid != null || uuid != "";
    }

    public static Cookie removeLoginCookie(Cookie[] cookies) {
        String uuid = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName() == "JSessionUUID") {
                cookie.setValue(null);
                return cookie;
            }
        }
        return null;
    }
}
