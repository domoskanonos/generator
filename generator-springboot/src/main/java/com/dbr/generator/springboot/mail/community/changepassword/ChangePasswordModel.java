package com.dbr.generator.springboot.mail.community.changepassword;

import lombok.Data;

import java.util.Locale;

@Data
public class ChangePasswordModel {

    private Locale locale = Locale.GERMAN;
    private String email;
    private String newPassword;

}
