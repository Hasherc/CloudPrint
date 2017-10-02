package com.hasherc.dao;

import com.hasherc.entity.UserAuth;
import com.hasherc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hasherc
 * @ 17-8-7
 */
@Repository
public interface UserDao {

    String loginByPhone(@Param("phoneNum") String phoneNum, @Param("password") String password);

    Integer findAuthStatusByPhoneNum(String phoneNum);

    int insertAuth(UserAuth userAuth);

    int insertUserInfo(UserInfo userInfo);

    void insertLoginTime(String uuid);

}
