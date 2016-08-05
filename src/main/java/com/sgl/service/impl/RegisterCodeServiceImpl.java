package com.sgl.service.impl;

import com.sgl.dao.RegisterCodeDao;
import com.sgl.model.RegisterCode;
import com.sgl.service.RegisterCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yao on 2016/8/5.
 */
@Service("RegisterCodeService")
@Transactional
public class RegisterCodeServiceImpl implements RegisterCodeService {

    //自动注入dao
    @Autowired
    private RegisterCodeDao<RegisterCode> registerCodeDao;

    public RegisterCode findRegisterCode(String mobile) {
        List<RegisterCode> ls = registerCodeDao.findRegisterCodeByMobile(mobile);
        return (ls.size() > 0 && ls != null)?ls.get(0):null;
    }

    public void saveRegisterCode(RegisterCode registerCode) {
        registerCodeDao.saveOrUpdate(registerCode);
    }
}
