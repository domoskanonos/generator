package com.dbr.generator.springboot.mail.community.resetpassword;

import lombok.Data;

import java.util.Locale;

@Data
public class ResetPasswordModel {

    private Locale locale = Locale.GERMAN;
    private String email;
    private String newPassword;

}
