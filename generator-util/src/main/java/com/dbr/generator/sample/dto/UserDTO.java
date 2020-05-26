package com.dbr.generator.sample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@EqualsAndHashCode
@ToString
public class UserDTO {

    private Integer id;

    private String name;

    private String username;

    private String password;

    private byte[] userImg;

}
