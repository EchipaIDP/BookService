package com.spring.catalogapp.service;

import com.spring.catalogapp.entity.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginUser myUser = null;

    public LoginUser getMyUser() {
        return myUser;
    }

    public void setMyUser(LoginUser myUser) {
        this.myUser = myUser;
    }
}
