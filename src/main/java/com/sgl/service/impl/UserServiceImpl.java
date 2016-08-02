package com.sgl.service.impl;

import com.sgl.dao.UserDaoI;
import com.sgl.model.User;
import com.sgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yao on 2016/7/29.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    //自动注入dao
    @Autowired
    private UserDaoI<User> userDao;

    public void addUser(User user) {
        userDao.save(user);
    }
}
