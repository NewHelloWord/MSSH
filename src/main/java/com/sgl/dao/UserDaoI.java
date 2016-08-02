package com.sgl.dao;

import com.sgl.model.User;

import java.io.Serializable;

/**
 * Created by Yao on 2016/7/29.
 */
public interface UserDaoI<T> extends BaseDao<User>{

    public Serializable save(T o);
}
