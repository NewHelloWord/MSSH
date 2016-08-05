package com.sgl.dao;

import com.sgl.model.RegisterCode;

import java.util.List;

/**
 * Created by Yao on 2016/8/5.
 */
public interface RegisterCodeDao<T> extends BaseDao<RegisterCode>{

    public List<RegisterCode> findRegisterCodeByMobile(String mobile);
}
