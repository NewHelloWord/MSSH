package com.sgl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yao on 2016/8/5.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "regist_code")
public class RegisterCode implements Serializable {

    @Id
    @Column(name = "id", length = 11)
    private int id;

    @Column(name = "mobile", length = 11)
    private String mobile;

    @Column(name = "code", length = 6)
    private String code;

    @Column(name = "invalid_time",columnDefinition="datetime")
    private Date invalidTime;

    @Column(name = "creat_time",columnDefinition="datetime")
    private Date creatTime;

    @Column(name = "isVaild") //0有效 1失效
    private String isVaild;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(String isVaild) {
        this.isVaild = isVaild;
    }
}
