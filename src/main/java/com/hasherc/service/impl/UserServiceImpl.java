package com.hasherc.service.impl;

import util.EncryptUtil;
import util.FileUtil;
import util.JsonUtil;
import util.UUIDUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hasherc.consts.StatusCode;
import com.hasherc.dao.UserDao;
import com.hasherc.entity.UserAuth;
import com.hasherc.entity.UserInfo;
import com.hasherc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hasherc
 * @ 17-8-7
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 登录
     *
     * @param params 手机号，密码组成的json
     * @return 登录成功 status = 1, user={user}
     * 浏览器传输参数有误 status = 1001
     * 登录过程中出现错误 status =1002
     * 用户不存在 status = 1004
     */
    @Override
    public String checkLogin(String params) {
        //检测json格式
        if (!JsonUtil.valid(params)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_JSON);
        }
        //校验参数
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("phoneNum", "String");
        paramsMap.put("password", "String");
        JSONObject paramsObj = JSON.parseObject(params);
        if (!JsonUtil.checkParams(paramsObj, paramsMap)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_PARAMS);
        }

        String phoneNum = paramsObj.getString("phoneNum");
        //密码加密
        String password = null;
        try {
            password = EncryptUtil.encryptSHA(paramsObj.getString("password"));
        } catch (Exception e) {
            return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
        }
        //根据手机号和密码获取用户uuid
        String userUuid = userDao.loginByPhone(phoneNum, password);

        if (userUuid == null) {
            return JsonUtil.resultToJson(StatusCode.STATUS_NOT_EXIST);
        }
        //更新登录时间
        userDao.insertLoginTime(userUuid);
        //组装json
        JSONObject resultObj = JsonUtil.resultToJsonObj(StatusCode.GLOBAL_SUCCESS);
        resultObj.put("userUuid", userUuid);

        return resultObj.toJSONString();
    }

    /**
     * 注册
     *
     * @param params 姓名，手机号，密码组成的json
     * @return 成功 status = 1
     * 浏览器发送的json格式有错 status = 1001
     * 注册过程出现错误 status = 1002
     * 用户已存在 status = 1007
     */
    @Override
    public String register(String params) {
        //检查json格式
        if (!JsonUtil.valid(params)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_JSON);
        }
        //参数校验
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("nickName", "String");
        paramsMap.put("phoneNum", "String");
        paramsMap.put("password", "String");
        JSONObject paramsObj = JSON.parseObject(params);
        if (!JsonUtil.checkParams(paramsObj, paramsMap)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_PARAMS);
        }

        //解析json
        String nickName = paramsObj.getString("nickName");
        System.out.println(nickName);
        String phoneNum = paramsObj.getString("phoneNum");
        //对密码进行加密
        String password = null;
        try {
            password = EncryptUtil.encryptSHA(paramsObj.getString("password"));
        } catch (Exception e) {
            return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
        }

        //检查用户名是否存在
        Integer resultStatus = userDao.findAuthStatusByPhoneNum(phoneNum);
        if (resultStatus != null && resultStatus == 1) {

            return JsonUtil.resultToJson(StatusCode.STATUS_DB_EXIST);

        }
        //组装bean
        UserAuth userAuth;
        String userUuid = UUIDUtil.getUUID();
        FileUtil.createUserDir(userUuid);
        userAuth = new UserAuth();
        userAuth.setPhoneNum(phoneNum);
        userAuth.setPassword(password);
        userAuth.setStatus(1);
        userAuth.setUserUuid(userUuid);
        //插入数据库用户权限表


        //插入数据库用户信息表
        UserInfo userInfo = new UserInfo();
        userInfo.setUserUuid(userUuid);
        userInfo.setBalance(0);
        userInfo.setNickName(nickName);
        userInfo.setPhoneNum(phoneNum);

        userDao.insertAuth(userAuth);
        userDao.insertUserInfo(userInfo);
        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);

    }


}
