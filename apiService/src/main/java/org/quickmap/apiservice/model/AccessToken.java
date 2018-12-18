package org.quickmap.apiservice.model;

public class AccessToken {

    private String userName;

    private String password;

    private int authLevel;

    private long createTime;

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

    public int getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static final class Builder {
        private String userName;
        private String password;
        private int authLevel;
        private long createTime;

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

        public Builder authLevel(int authLevel) {
            this.authLevel = authLevel;
            return this;
        }

        public Builder createTime(long createTime) {
            this.createTime = createTime;
            return this;
        }

        public AccessToken build() {
            AccessToken accessToken = new AccessToken();
            accessToken.setUserName(userName);
            accessToken.setPassword(password);
            accessToken.setAuthLevel(authLevel);
            accessToken.setCreateTime(createTime);
            return accessToken;
        }
    }
}
