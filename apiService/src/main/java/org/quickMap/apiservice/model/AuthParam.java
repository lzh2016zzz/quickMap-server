package org.quickMap.apiservice.model;

/**
 * 登录校验参数
 */
public class AuthParam {

    private String loginName;

    private String password;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static final class Builder {
        private String loginName;
        private String password;

        private Builder() {
        }

        public static Builder Builder() {
            return new Builder();
        }

        public Builder loginName(String loginName) {
            this.loginName = loginName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public AuthParam build() {
            AuthParam authParam = new AuthParam();
            authParam.setLoginName(loginName);
            authParam.setPassword(password);
            return authParam;
        }
    }
}
