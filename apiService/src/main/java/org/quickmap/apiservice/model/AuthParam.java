package org.quickmap.apiservice.model;

/**
 * 校验参数
 */
public class AuthParam {

    private String userName;

    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static final class Builder {
        private String userName;
        private String password;

        private Builder() {
        }

        public static Builder Builder() {
            return new Builder();
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public AuthParam build() {
            AuthParam authParam = new AuthParam();
            authParam.setUserName(userName);
            authParam.setPassword(password);
            return authParam;
        }
    }
}
