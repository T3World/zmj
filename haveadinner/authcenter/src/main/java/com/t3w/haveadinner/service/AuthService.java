package com.t3w.haveadinner.service;

import com.t3w.haveadinner.exception.TheBadTokenException;
import com.t3w.haveadinner.pojo.UserInfo;

public interface AuthService {

    UserInfo verfyToken(String token) throws TheBadTokenException;

}
