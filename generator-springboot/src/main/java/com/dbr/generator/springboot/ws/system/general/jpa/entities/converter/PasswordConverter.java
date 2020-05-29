package com.dbr.generator.springboot.ws.system.general.jpa.entities.converter;

import com.dbr.generator.springboot.ws.util.SecurityUtil;

import javax.persistence.AttributeConverter;

/**
 * use me on string password fields:
 *
 * @Convert(converter = PasswordConverter.class)
 */
public class PasswordConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String decrypted) {
        return SecurityUtil.getUniqueInstance().encrypt(decrypted);
    }


    @Override
    public String convertToEntityAttribute(String encrypted) {
        return SecurityUtil.getUniqueInstance().decrypt(encrypted);
    }


}
