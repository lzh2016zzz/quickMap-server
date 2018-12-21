package org.quickMap.apiservice.model;

/**
 * 创建用户参数
 */
public class CreateUserParam {


    private String loginName;
    private String password;
    private String userName;
    private String resetPasswordAnswer;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResetPasswordAnswer() {
        return resetPasswordAnswer;
    }

    public void setResetPasswordAnswer(String resetPasswordAnswer) {
        this.resetPasswordAnswer = resetPasswordAnswer;
    }
}
