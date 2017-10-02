package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hasherc
 * @ 17-8-8
 */
public class RegexUtil {

    // 手机号检验正则
    private static final String REGEX_PHONE = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
    // 邮箱检验正则
    private static final String REGEX_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";


    public static boolean validEmail(String param) {
        Pattern regex_email = Pattern.compile(REGEX_EMAIL);

        Matcher matcher = regex_email.matcher(param);

        return matcher.matches();

    }

    public static boolean validPhone(String param) {
        Pattern regex_phone = Pattern.compile(REGEX_PHONE);

        Matcher matcher = regex_phone.matcher(param);
        return matcher.matches();

    }
}
