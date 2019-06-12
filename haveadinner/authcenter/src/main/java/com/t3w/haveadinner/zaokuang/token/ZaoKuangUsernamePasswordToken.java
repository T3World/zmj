package com.t3w.haveadinner.zaokuang.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ZaoKuangUsernamePasswordToken extends UsernamePasswordToken {

    public ZaoKuangUsernamePasswordToken() {
    }

    public ZaoKuangUsernamePasswordToken(String username, char[] password) {
        super(username, password);
    }

    public ZaoKuangUsernamePasswordToken(String username, String password) {
        super(username, password);
    }

    public ZaoKuangUsernamePasswordToken(String username, char[] password, String host) {
        super(username, password, host);
    }

    public ZaoKuangUsernamePasswordToken(String username, String password, String host) {
        super(username, password, host);
    }

    public ZaoKuangUsernamePasswordToken(String username, char[] password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public ZaoKuangUsernamePasswordToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public ZaoKuangUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public ZaoKuangUsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }


}
