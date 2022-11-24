package top.yuchat.patch.server.oauth2;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class UserToken implements RememberMeAuthenticationToken {

    private String token;


    public UserToken(String token) {
        this.token = token;
    }

    @Override
    public boolean isRememberMe() {
        return false;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
