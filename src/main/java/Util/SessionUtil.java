package Util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author hasherc
 * @ 17-8-8
 */
public class SessionUtil {

    public static void setSession(HttpSession session, JSONObject userObject) {

        session.setAttribute("uuid", userObject.getString("uuid"));
        session.setAttribute("nickName", userObject.getString("nickName"));
        session.setAttribute("directory", userObject.getString("directory"));


    }

    public static String getUserId(HttpSession session) {
        if (session == null) {
            return null;
        }
        return session.getAttribute("uuid").toString();
    }
}
