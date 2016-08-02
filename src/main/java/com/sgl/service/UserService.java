package com.sgl.service;

import com.sgl.dao.UserDaoI;
import com.sgl.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yao on 2016/7/29.
 */
public interface UserService {

    public void addUser(User user) ;
}
