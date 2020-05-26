package com.dbr.generator.sample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@Data
@EqualsAndHashCode
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "USERNAME")
    private String username;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Lob
    @Column(name = "USER_IMG")
    private byte[] userImg;


    @Column(name = "BIRTHDAY")
    private Date birthday;

}
