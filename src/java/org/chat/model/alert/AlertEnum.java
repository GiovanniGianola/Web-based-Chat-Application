/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.alert;

/**
 *
 * @author giano
 */
public enum AlertEnum {

    CODE_FAILNAME("Username already taken."),
    CODE_SIGNUP_FAILURE("Password and Username fields can't be empty."),
    CODE_LOGIN_FAILURE("Login failed due to incorrect password or inexistent account.<br>Try again or signup a new account"),
    CODE_LOGIN_FIELDEMPTY("Password or Username field can't be empty."),
    CODE_NAMEEMPTY("Username field can not be empty."),
    CODE_PSWEMPTY("Password field can't be empty."),
    CODE_REPSWEMPTY("Repeat Password field can't be empty."),
    CODE_PSWNOTEQUAL("Password doesn't match.");

    private final String message;

    private AlertEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
