package com.example.form;

/**
 * Administrator
 *  管理者情報を表すドメイン.
 */

public class LoginForm {
    
    private String mailAddress;//メールアドレス
    private String password;//パスワード


    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Override
    public String toString() {
        return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
    }

    
    
}
