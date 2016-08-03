package com.sgl.dao.impl;

import com.sgl.dao.UserDaoI;
import com.sgl.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yao on 2016/7/29.
 */
@Repository("userDao")
public class UserDaoImpl<T> extends BaseDaoImpl<User> implements UserDaoI<T> {

    //注入sessionfactory
    @Autowired
    private SessionFactory sessionFactory;

    public Serializable save(T o) {
        return sessionFactory.getCurrentSession().save(o);
    }

    public List<User> find(User user) {
        String hql = " from User u where u.username =:username ";
        Query qy = sessionFactory.getCurrentSession().createQuery(hql);
        qy.setParameter("username",user.getUsername());
        return qy.list();
    }
}
