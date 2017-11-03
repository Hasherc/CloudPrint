package com.qming.dao;

import com.qming.entity.UserAuth;
import com.qming.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qming
 * @ 17-8-7
 */
@Repository
public interface UserDao {

    String loginByPhone(@Param("phoneNum") String phoneNum, @Param("password") String password);

    Integer findAuthStatusByPhoneNum(String phoneNum);

    int insertAuth(UserAuth userAuth);

    int insertUserInfo(UserInfo userInfo);

    void insertLoginTime(String userUuid);

    UserInfo findUserInfo(String userUuid);

}
