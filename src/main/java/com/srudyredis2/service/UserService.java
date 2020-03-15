package com.srudyredis2.service;

import com.srudyredis2.model.User;

/**
 * @author 郑悦恺
 * @Classname UserService
 * @Description TODO
 * @Date 2020/3/10 13:37
 */

public interface UserService {

    String getString(String key);
    User selectById(String id);

}
