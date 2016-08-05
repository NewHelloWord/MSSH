package com.sgl.dao.impl;

import com.sgl.dao.RegisterCodeDao;
import com.sgl.model.RegisterCode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yao on 2016/8/5.
 */
@Repository("RegisterCodeDao")
public class RegisterCodeDaoImpl<T> extends BaseDaoImpl<RegisterCode> implements RegisterCodeDao<T> {

    //注入sessionfactory
    @Autowired
    private SessionFactory sessionFactory;

    public List<RegisterCode> findRegisterCodeByMobile(String mobile) {
       String hql = " FROM RegisterCode r WHERE r.mobile =:mobile and r.isVaild = 0 order by r.creatTime desc";
        Query qy = sessionFactory.getCurrentSession().createQuery(hql);
        qy.setParameter("mobile",mobile);
        return qy.list();
    }
}
