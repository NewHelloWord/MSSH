package com.sgl.service;

import com.sgl.model.RegisterCode;

/**
 * Created by Yao on 2016/8/5.
 */
public interface RegisterCodeService {

    public RegisterCode findRegisterCode(String mobile);

    public void saveRegisterCode(RegisterCode registerCode);
}
