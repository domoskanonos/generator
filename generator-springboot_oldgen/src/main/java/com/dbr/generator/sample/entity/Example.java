package com.dbr.generator.sample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "EXAMPLE")
@Data
@EqualsAndHashCode
public class Example {

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

}
