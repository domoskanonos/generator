package com.dbr.generator.springboot.system.auth.entity;

import com.dbr.generator.springboot.system.auth.enumeration.AuthPrivilegeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_PRIVILEGE")
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class AuthUserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "NAME", unique = true, nullable = false)
    private AuthPrivilegeEnum name;

    public AuthUserPrivilege(AuthPrivilegeEnum name) {
        this.name = name;
    }

}
