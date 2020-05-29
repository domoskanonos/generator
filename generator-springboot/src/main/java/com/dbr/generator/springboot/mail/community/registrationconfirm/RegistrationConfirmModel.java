package com.dbr.generator.springboot.mail.community.registrationconfirm;

import lombok.Data;

import java.util.Locale;

@Data
public class RegistrationConfirmModel {

    private Locale locale = Locale.GERMAN;
    private String email;
    private String linkActivate;
    private String linkDelete;

}
