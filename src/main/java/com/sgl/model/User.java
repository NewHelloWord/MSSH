package com.sgl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yao on 2016/7/29.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tuser")
public class User implements Serializable {

    @Id
    @Column(name = "id", nullable = false, length = 11)
    private int id;

    @Column(name = "uid", nullable = false, length = 50)
    private String uid;

    @Column(name = "username", unique = false, nullable = false, length = 100)
    private String username;

    @Column(name="password",nullable=false,length=100)
    private String password;

    @Transient
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
