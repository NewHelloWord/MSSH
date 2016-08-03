package com.sgl.dao;

import com.sgl.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yao on 2016/7/29.
 */
public interface UserDaoI<T> extends BaseDao<User>{

    public Serializable save(T o);

    public List<User> find(User user);
}
