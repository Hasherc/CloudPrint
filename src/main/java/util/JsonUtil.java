package util;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * @author hasherc
 * @ 17-8-8
 */
public class JsonUtil {

    /**
     * 根据状态码组装返回结果
     *
     * @param statusCode 状态码
     * @return json字符串
     */
    public static String resultToJson(int statusCode) {
        JSONObject result = new JSONObject();
        result.put("status", statusCode);
        return result.toString();
    }

    /**
     * 根据状态码组装返回结果
     *
     * @param statusCode 状态码
     * @return json对象
     */
    public static JSONObject resultToJsonObj(int statusCode) {
        JSONObject result = new JSONObject();
        result.put("status", statusCode);
        return result;
    }

    /**
     * 验证字符串是否为json格式
     *
     * @param json 待验证的字符串
     * @return 是否通过校验
     */
    public static boolean valid(String json) {
        if (null == json) {
            return false;
        }

        try {
            JSONObject.parse(json);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 检查json中是否含有指定参数
     *
     * @param json      json字符串
     * @param paramsMap 参数集合
     * @return 检查结果
     */
    public static boolean checkParams(JSONObject json, Map<String, String> paramsMap) {
        if (null == json || null == paramsMap) {
            return false;
        }

        Iterator<Map.Entry<String, String>> iter = paramsMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String param = entry.getKey();
            String paramType = entry.getValue();

            if (!json.containsKey(param)) {
                return false;
            }

            if (!checkParamValid(json.getString(param), paramType)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查参数的数据类型是否合法
     *
     * @param param     参数名
     * @param paramType 参数数据类型
     * @return 校验结果
     */
    private static boolean checkParamValid(String param, String paramType) {
        if (null == param || null == paramType) {
            return false;
        }

        try {
            if ("String".equals(paramType)) {
                return true;
            }
            if ("Long".equals(paramType)) {
                Long.parseLong(param);
                return true;
            }
            if ("Integer".equals(paramType)) {
                Integer.parseInt(param);
                return true;
            }
            if ("Double".equals(paramType)) {
                Double.parseDouble(param);
                return true;
            }
            if ("Boolean".equals(paramType)) {
                Boolean.parseBoolean(param);
                return true;
            }


        } catch (NumberFormatException e) {
            return false;
        }

        return false;

    }

}
